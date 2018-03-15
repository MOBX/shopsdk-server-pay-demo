package com.mob.etrade.server.demo.controller;

import com.mob.etrade.server.demo.MobApiException;
import com.mob.etrade.server.demo.bean.*;
import com.mob.etrade.server.demo.config.MobProperties;
import com.mob.etrade.server.demo.constant.MobStatusConstant;
import com.mob.etrade.server.demo.constant.RefundStatusEnum;
import com.mob.etrade.server.demo.pay.wxpay.reqeust.WxRefundRequest;
import com.mob.etrade.server.demo.pay.wxpay.response.WxRefundResponse;
import com.mob.etrade.server.demo.service.MobRefundService;
import com.mob.etrade.server.demo.service.WxPayService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * 微信退款控制器
 * <p>
 * describe:
 *
 * @author yunkai(xianyi)
 * <p>
 * date 2017/12/15
 */
@Controller
@RequestMapping("/refund/wx")
public class WxRefundController {
    @Resource
    private WxPayService wxPayService;
    @Resource
    private MobProperties mobProperties;
    @Resource
    private MobRefundService mobRefundService;


    @PostMapping(value = "refund", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Object refund(@RequestBody Refund refund) {
        //1. 发起退款申请
        CustomResult<WxRefundResponse> customResult = wxPayRefund(refund);

        //退款成功
        if (customResult.isSuccess()) {
            refund.setStatus(RefundStatusEnum.SUCCESS.getCode());
        } else {
            refund.setStatus(RefundStatusEnum.FAIL.getCode());
        }
        // TODO: 保存退款记录，在通知Mob服务器失败之后可以发起重试
        updateRefundStatus(Collections.singletonList(refund));
        return customResult;
    }

    /**
     * 示例中采用手动退款的方式
     * TODO: 开发者可通过loop pull refundList接口的方式实现自动退款逻辑
     * @return
     */
    @GetMapping("refundList.html")
    public String refundList() {
        return "refund_list";
    }

    @GetMapping(value = "refundList", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Object refundList(OrderQuery orderQuery) {
        orderQuery.setAppkey(mobProperties.getAppKey());
        MobResult<RefundPage> mobResult = mobRefundService.queryRefundList(orderQuery);

        if (mobResult != null && MobStatusConstant.SUCCESS.equals(mobResult.getStatus())) {
            return new CustomResult<>(true, mobResult.getData());
        }
        return new CustomResult<>(mobResult != null? mobResult.getMessage(): "fail");
    }

    /**
     * 通知mob sdk server退款状态
     * @param refundList 退单列表
     */
    private void updateRefundStatus(List<Refund> refundList) {
        BatchRefund batchRefund = new BatchRefund();
        batchRefund.setAppkey(mobProperties.getAppKey());
        batchRefund.setRefundList(refundList);
        MobResult mobResult = mobRefundService.updateRefundStatus(batchRefund);
        if (mobResult == null || !MobStatusConstant.SUCCESS.equals(mobResult.getStatus())) {
            throw new MobApiException(mobResult != null ? mobResult.getMessage() : "mob server updateRefundStatus request fail");
        }
    }

    private CustomResult<WxRefundResponse> wxPayRefund(Refund refund) {
        // TODO: 退款逻辑校验
        if (refund.getRefundFee()> refund.getOrderPaidMoney()) {
            return new CustomResult<>("退款金額不能大於支付金額");
        }
        WxRefundRequest request = new WxRefundRequest();
        request.setRefundFee(refund.getRefundFee());
        request.setOutRefundNo(refund.getRefundCommodityId());
        request.setOutTradeNo(refund.getOrderId());
        request.setTotalFee(refund.getOrderPaidMoney());

        WxRefundResponse response = wxPayService.refund(request);
        return new WxResponseConverter<WxRefundResponse>().convert(response);
    }
}
