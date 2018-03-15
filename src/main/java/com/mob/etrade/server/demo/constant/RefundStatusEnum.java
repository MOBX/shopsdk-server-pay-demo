package com.mob.etrade.server.demo.constant;

/**
 * Created by sing on 2017/12/23.
 * desc:
 */
public enum RefundStatusEnum {

    SUCCESS(0),
    FAIL(1)
    ;
    private Integer code;

    RefundStatusEnum(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
