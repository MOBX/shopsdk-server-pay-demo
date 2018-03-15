package com.mob.etrade.server.demo.pay.wxpay;

/**
 * @author yunkai(xianyi)
 * describe: 微信支付
 * @date 2017/12/23
 */
public interface WxPayClient {
    /**
     * 微信支付执行器
     * @param <T> 微信支付返回对象
     * @param <P> 微信支付请求对象
     * @return 微信支付返回对象
     * @param useCert 是否使用证书
     */
    <T extends BaseWxResponse, P extends BaseWxReqeust<T>> T execute(P wxRequest, boolean useCert);

    /**
     * 添加签名
     * @param wxRequest 微信支付请求对象
     * @param <P> BaseWxRequest 子类型
     */
    <P extends BaseWxReqeust> void addSign(P wxRequest);
}
