package com.mob.etrade.server.demo.bean;

import java.util.List;

/**
 * describe:
 *
 * @author yunkai(xianyi)
 * <p>
 * date 2017/12/29
 */
public class BatchRefund {

    private String appkey;
    private List<Refund> refundList;

    public String getAppkey() {
        return appkey;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }

    public List<Refund> getRefundList() {
        return refundList;
    }

    public void setRefundList(List<Refund> refundList) {
        this.refundList = refundList;
    }
}
