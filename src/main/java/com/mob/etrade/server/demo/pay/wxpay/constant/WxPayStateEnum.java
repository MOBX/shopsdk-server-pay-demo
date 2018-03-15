package com.mob.etrade.server.demo.pay.wxpay.constant;

/**
 * describe:
 *
 * @author yunkai(xianyi)
 * <p>
 * date 2017/12/12
 */
public enum WxPayStateEnum {

    SUCCESS("SUCCESS"),
    FAIL("FAIL")
    ;
    private String code;

    WxPayStateEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
