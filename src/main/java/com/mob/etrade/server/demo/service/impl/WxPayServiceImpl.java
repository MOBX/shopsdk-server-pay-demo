package com.mob.etrade.server.demo.service.impl;

import com.mob.etrade.server.demo.pay.wxpay.WxPayClient;
import com.mob.etrade.server.demo.pay.wxpay.reqeust.WxRefundRequest;
import com.mob.etrade.server.demo.pay.wxpay.reqeust.WxUnifiedOrderRequest;
import com.mob.etrade.server.demo.pay.wxpay.response.WxRefundResponse;
import com.mob.etrade.server.demo.pay.wxpay.response.WxUnifiedOrderResponse;
import com.mob.etrade.server.demo.service.WxPayService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * describe: 
 * @see com.mob.etrade.server.demo.service.WxPayService 
 * 定义接口
 * @see WxPayClient
 *
 * @author yunkai(xianyi)
 * <p>
 * date 2017/12/14
 */
@Component
public class WxPayServiceImpl implements WxPayService {

    @Resource
    private WxPayClient customWxPayClient;

    @Override
    public WxUnifiedOrderResponse unifiedOrder(WxUnifiedOrderRequest wxUnifiedOrderRequest) {
        return customWxPayClient.execute(wxUnifiedOrderRequest, false);
    }


    @Override
    public WxRefundResponse refund(WxRefundRequest wxRefundRequest) {
        return customWxPayClient.execute(wxRefundRequest, true);
    }

}
