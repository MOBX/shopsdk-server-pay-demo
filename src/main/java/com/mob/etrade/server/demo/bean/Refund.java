package com.mob.etrade.server.demo.bean;

/**
 * 退单bean
 * <p>
 * describe:
 *
 * @author yunkai(xianyi)
 * <p>
 * date 2017/12/18
 */
public class Refund {

    /**
     * 退款金额，单位分
     */
    private Integer refundFee;
    /**
     * 订单金额，单位分
     */
    private Integer orderPaidMoney;
    /**
     * 订单id
     */
    private String orderId;
    /**
     * 退款商品唯一标识
     */
    private String refundCommodityId;
    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getOrderPaidMoney() {
        return orderPaidMoney;
    }

    public void setOrderPaidMoney(Integer orderPaidMoney) {
        this.orderPaidMoney = orderPaidMoney;
    }

    public Integer getRefundFee() {
        return refundFee;
    }

    public void setRefundFee(Integer refundFee) {
        this.refundFee = refundFee;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getRefundCommodityId() {
        return refundCommodityId;
    }

    public void setRefundCommodityId(String refundCommodityId) {
        this.refundCommodityId = refundCommodityId;
    }

    @Override
    public String toString() {
        return "Refund{" +
                "refundFee=" + refundFee +
                ", orderPaidMoney=" + orderPaidMoney +
                ", orderId='" + orderId + '\'' +
                ", refundCommodityId='" + refundCommodityId + '\'' +
                ", status=" + status +
                '}';
    }
}
