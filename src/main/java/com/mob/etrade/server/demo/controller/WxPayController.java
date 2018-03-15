package com.mob.etrade.server.demo.controller;

import com.mob.etrade.server.demo.MobApiException;
import com.mob.etrade.server.demo.MobException;
import com.mob.etrade.server.demo.bean.CustomResult;
import com.mob.etrade.server.demo.bean.MobResult;
import com.mob.etrade.server.demo.bean.OrderInfo;
import com.mob.etrade.server.demo.config.MobProperties;
import com.mob.etrade.server.demo.config.WxPayProperties;
import com.mob.etrade.server.demo.constant.MobStatusConstant;
import com.mob.etrade.server.demo.constant.PayStatusEnum;
import com.mob.etrade.server.demo.dto.WxUnifiedOrderDTO;
import com.mob.etrade.server.demo.pay.util.SignUtil;
import com.mob.etrade.server.demo.pay.wxpay.constant.WxPayStateEnum;
import com.mob.etrade.server.demo.pay.wxpay.reqeust.WxUnifiedOrderRequest;
import com.mob.etrade.server.demo.pay.wxpay.response.WxNotifyResponse;
import com.mob.etrade.server.demo.pay.wxpay.response.WxNotifyResponseRep;
import com.mob.etrade.server.demo.pay.wxpay.response.WxUnifiedOrderResponse;
import com.mob.etrade.server.demo.service.MobOrderService;
import com.mob.etrade.server.demo.service.WxPayService;
import com.mob.etrade.server.demo.util.CacheUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import static com.mob.etrade.server.demo.pay.wxpay.constant.WxPayStateEnum.FAIL;
import static com.mob.etrade.server.demo.pay.wxpay.constant.WxPayStateEnum.SUCCESS;

/**
 * 微信支付控制器
 * <p>
 * describe: 微信支付流程相
 *
 * @author yunkai(xianyi)
 * <p>
 * date 2017/12/12
 */

@RestController
@RequestMapping(value = "/pay/wx", produces = MediaType.APPLICATION_JSON_VALUE)
public class WxPayController {
    private static final Logger LOGGER = LoggerFactory.getLogger(WxPayController.class);

    @Resource
    private WxPayProperties wxPayProperties;
    @Resource
    private MobProperties mobProperties;
    @Resource
    private WxPayService wxPayService;
    @Resource
    private MobOrderService mobOrderService;

    private static final Long ABOUT_TWO_HOURS = 110_000L;


    @PostMapping(value = "unifiedorder")
    public Object unifiedorder(@RequestBody WxUnifiedOrderDTO wxUnifiedOrderDTO) {
        // TODO: 1. wxUnifiedOrderDTO参数校验

        //用订单的主要信息做缓存的key，也可以直接获取wxUnifiedOrderDTO的outTradeNo + hashCode 做key
        String cacheKey = StringUtils.joinWith("-", wxUnifiedOrderDTO.getOutTradeNo(), wxUnifiedOrderDTO.getTotalFee());

        Map<String, String> prePayInfo = CacheUtils.get(cacheKey);

        if (!CollectionUtils.isEmpty(prePayInfo)) {
            LOGGER.info("unifiedorder prepay info hit cache {}", prePayInfo);
            return new CustomResult<>( true, prePayInfo);
        }
        WxUnifiedOrderRequest request = wxUnifiedOrderDTO.convertToWxUnifiedOrderRequest();

        request.setNotifyUrl(wxPayProperties.getNotifyUrl());
        //2. 在发起微信统一下单请求之前，应对订单信息做校验。
        checkOrderInfo(wxUnifiedOrderDTO);
        //3 订单号加随机数，是否添加应由实际情况决定
        request.setOutTradeNo(createNoneOrderId(wxUnifiedOrderDTO.getOutTradeNo()));
        //3. 发起微信统一下单请求
        WxUnifiedOrderResponse response = wxPayService.unifiedOrder(request);
        CustomResult<WxUnifiedOrderResponse> customResult = new WxResponseConverter<WxUnifiedOrderResponse>().convert(response);
        if (!customResult.isSuccess()) {
            //如果返回信息校验失败，直接返回
            return customResult;
        }

        prePayInfo = new TreeMap<>();
        prePayInfo.put("appid", response.getAppid());
        prePayInfo.put("partnerid", response.getMchid());
        prePayInfo.put("prepayid", response.getPrepayId());
        prePayInfo.put("package", "Sign=WXPay");
        prePayInfo.put("noncestr", SignUtil.createNoneStr());
        //毫秒-> 秒
        prePayInfo.put("timestamp", System.currentTimeMillis() / 1_000 + "");
        prePayInfo.put("sign", SignUtil.createSign(prePayInfo, wxPayProperties.getSecret()));
        //存入緩存，以备重新发起支付。  預支付信息2小時內有效。
        CacheUtils.put(ABOUT_TWO_HOURS, cacheKey, prePayInfo);

        return new CustomResult<>( true, prePayInfo);
    }

    /**
     * 微信支付通知接口
     * @param wxNotifyResponse 微信支付通知信息
     * @return SUCCESS or FAIL
     */
    @PostMapping(value = "notify", produces = MediaType.TEXT_XML_VALUE)
    public Object resultNotify(@RequestBody WxNotifyResponse wxNotifyResponse) {
        WxNotifyResponseRep notifyResponseRep = new WxNotifyResponseRep();
        notifyResponseRep.setReturnCode(FAIL.getCode());
        //1. 判断微信请求是否成功
        if (WxPayStateEnum.FAIL.getCode().equals(wxNotifyResponse.getReturnCode())) {
            LOGGER.error("wx notify fail response: {}", wxNotifyResponse);
            return notifyResponseRep;
        } else if (!SignUtil.verifySign(wxNotifyResponse, wxPayProperties.getSecret())) {
            //2. 验签，验签不通过，返回fail
            LOGGER.error("wx notify verify fail response: {}", wxNotifyResponse);
            return notifyResponseRep;
        }
        // TODO: 保存订单支付信息
        notifyResponseRep.setReturnCode(SUCCESS.getCode());
        // 3. 更新mob订单状态
        updateMobOrderPayStatus(wxNotifyResponse);

        return notifyResponseRep;
    }

    /**
     * 从mob sdk服务器获取订单信息，并做简单的校验。
     * @param wxUnifiedOrderDTO 前端传递的订单参数
     */
    private void checkOrderInfo(WxUnifiedOrderDTO wxUnifiedOrderDTO) {
        OrderInfo order = new OrderInfo();
        order.setAppkey(mobProperties.getAppKey());
        order.setOrderId(wxUnifiedOrderDTO.getOutTradeNo());

        MobResult<OrderInfo> mobResult = mobOrderService.queryOrderDetail(order);

        if (mobResult != null && mobResult.getData() != null) {
            OrderInfo orderInfo = mobResult.getData();
            // TODO: 数据校验
            if (wxUnifiedOrderDTO.getTotalFee().equals(orderInfo.getPaidMoney())) {
                return;
            }
        }
        //数据校验不通过抛出异常
        throw new MobApiException("verify order info fail");
    }

    /**
     *
     * 通知mob 当前订单支付状态
     * @param wxNotifyResponse 微信通知信息
     */
    private void updateMobOrderPayStatus(WxNotifyResponse wxNotifyResponse) {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setAppkey(mobProperties.getAppKey());
        orderInfo.setTotalMoney(wxNotifyResponse.getTotalFee());
        orderInfo.setPaidMoney(wxNotifyResponse.getTotalFee());
        //将微信订单号，作为支付凭证
        orderInfo.setPayTicket(wxNotifyResponse.getTransactionId());
        //获取原来的mob订单号
        orderInfo.setOrderId(getSourceOrderId(wxNotifyResponse.getOutTradeNo()));

        if (SUCCESS.getCode().equals(wxNotifyResponse.getReturnCode()) &&
                SUCCESS.getCode().equals(wxNotifyResponse.getResultCode())) {
            orderInfo.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        } else {
            orderInfo.setPayStatus(PayStatusEnum.FAIL.getCode());
        }

        try {
            MobResult mobResult = mobOrderService.updateOrderStatus(orderInfo);
            if (!MobStatusConstant.SUCCESS.equals(mobResult.getStatus())) {
                LOGGER.error("update order fail");
            }
        } catch (MobException e) {
            LOGGER.error("updateMobOrderPayStatus fail {}", wxNotifyResponse, e);
            // TODO: 做好异常处理，确保通知到位
        }
    }

    /**
     * 在订单后拼接一个随机数，在缓存失效后（预支付信息过期了），或者订单信息有变化的情况下
     * 能够重新拉取预支付信息
     * @param sourceOrderId 原id
     * @return 有随机数的id
     */
    private String createNoneOrderId(String sourceOrderId) {
        Random random = new Random();
        //订单后拼接随机数
        return StringUtils.joinWith("_", sourceOrderId, random.nextInt(1000));
    }

    /**
     * 去除随机数，获取原始的订单号
     * @param outTradeNo 微信商户订单号
     * @return 原始商户订单号
     */
    private String getSourceOrderId(String outTradeNo) {
        return outTradeNo.split("_")[0];
    }
}
