package com.mob.etrade.server.demo.constant;

/**
 * 电商SDK支付结果
 * <p>
 * describe:
 *
 * @author yunkai(xianyi)
 * <p>
 * date 2017/12/19
 */
public enum PayStatusEnum {
    /**
     * 成功
     */
    SUCCESS(1),
    /**
     * 失败
     */
    FAIL(2),
    /**
     * 取消
     */
    CANCEL(3)
    ;
    private Integer code;

    PayStatusEnum(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
