package com.mob.etrade.server.demo.pay.wxpay;

import com.mob.etrade.server.demo.pay.PayRequest;

/**
 * @author xianyi(max)
 * describe: 微信请求对象
 * @date 2017/12/16
 */
public interface WxPayRequest<T extends BaseWxResponse> extends PayRequest<T> {
}
