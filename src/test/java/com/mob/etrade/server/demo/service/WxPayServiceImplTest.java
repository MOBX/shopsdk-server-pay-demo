package com.mob.etrade.server.demo.service;

import com.mob.etrade.server.demo.pay.wxpay.BaseWxReqeust;
import com.mob.etrade.server.demo.pay.wxpay.constant.WxPayStateEnum;
import com.mob.etrade.server.demo.pay.wxpay.reqeust.WxRefundRequest;
import com.mob.etrade.server.demo.pay.wxpay.reqeust.WxUnifiedOrderRequest;
import com.mob.etrade.server.demo.pay.wxpay.response.WxRefundResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * describe:
 *
 * @author yunkai(xianyi)
 * <p>
 * date 2017/12/14
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class WxPayServiceImplTest {

    @Resource
    private WxPayService wxPayService;

    @Test
    public void testRefund() {
        WxRefundRequest refundRequest = new WxRefundRequest();
        refundRequest.setOutTradeNo("1217752501201407033233368018");
        refundRequest.setOutRefundNo("1217752501201407033233368018");
        refundRequest.setTotalFee(201);
        refundRequest.setRefundFee(201);
        WxRefundResponse response = wxPayService.refund(refundRequest);
        Assert.assertEquals(WxPayStateEnum.SUCCESS.getCode(), response.getReturnCode());
        Assert.assertEquals(WxPayStateEnum.SUCCESS.getCode(), response.getResultCode());
    }

    @Test
    public void testExecute() {
        excute(new WxUnifiedOrderRequest());
    }

    private void excute(BaseWxReqeust baseReqeust) {
        System.out.println(baseReqeust.getClass());
    }
}
