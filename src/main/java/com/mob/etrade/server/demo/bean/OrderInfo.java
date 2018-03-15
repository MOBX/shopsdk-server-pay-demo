package com.mob.etrade.server.demo.bean;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 订单支付信息
 * <p>
 * describe:
 *
 * @author yunkai(xianyi)
 * <p>
 * date 2017/12/19
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderInfo {
    private String orderId;
    private Integer totalMoney;
    private Integer paidMoney;
    private Integer payStatus;
    private String appkey;
    private String payTicket;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Integer totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Integer getPaidMoney() {
        return paidMoney;
    }

    public void setPaidMoney(Integer paidMoney) {
        this.paidMoney = paidMoney;
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    public String getAppkey() {
        return appkey;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }

    public String getPayTicket() {
        return payTicket;
    }

    public void setPayTicket(String payTicket) {
        this.payTicket = payTicket;
    }

    @Override
    public String toString() {
        return "OrderInfo{" +
                "orderId='" + orderId + '\'' +
                ", totalMoney=" + totalMoney +
                ", paidMoney=" + paidMoney +
                ", payStatus=" + payStatus +
                ", appkey='" + appkey + '\'' +
                ", payTicket='" + payTicket + '\'' +
                '}';
    }
}
