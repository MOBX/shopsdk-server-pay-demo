package com.mob.etrade.server.demo.pay.wxpay.constant;

/**
 * describe:
 *
 * @author yunkai(xianyi)
 * <p>
 * date 2017/12/12
 */
public class WxPayConstant {

    public enum SignType {
        MD5, HMACSHA256
    }

    /**
     * weix 统一下单接口
     */
    public static final String UNIFIEDORDER_API = "https://api.mch.weixin.qq.com/sandboxnew/pay/unifiedorder";
    private static final String NOTIFY_URL = "/pay/wx/notify";


}
