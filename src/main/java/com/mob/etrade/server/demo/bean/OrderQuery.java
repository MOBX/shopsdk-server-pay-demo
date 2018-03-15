package com.mob.etrade.server.demo.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

/**
 * 微信退款查询条件
 * <p>
 * describe:
 *
 * @author yunkai(xianyi)
 * <p>
 * date 2017/12/18
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderQuery {

    @JsonUnwrapped
    private PageInfo pageInfo;
    /**
     * mob appkey
     */
    private String appkey;
    /**
     * 订单开始时间
     */
    private String orderBeginAt;
    /**
     * 订单结束时间
     */
    private String orderEndAt;
    /**
     * 订单号
     */
    private String orderId;
    /**
     * 商品名称(可做模糊查询)
     */
    private String productName;
    /**
     * 退款申请时间Begin
     */
    private String refundApplyBeginDate;
    /**
     * 退款申请时间End
     */
    private String refundApplyEndDate;
    /**
     * 用户ID（第三方系统用户ID）
     */
    private String buyerId;

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public String getAppkey() {
        return appkey;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }

    public String getOrderBeginAt() {
        return orderBeginAt;
    }

    public void setOrderBeginAt(String orderBeginAt) {
        this.orderBeginAt = orderBeginAt;
    }

    public String getOrderEndAt() {
        return orderEndAt;
    }

    public void setOrderEndAt(String orderEndAt) {
        this.orderEndAt = orderEndAt;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getRefundApplyBeginDate() {
        return refundApplyBeginDate;
    }

    public void setRefundApplyBeginDate(String refundApplyBeginDate) {
        this.refundApplyBeginDate = refundApplyBeginDate;
    }

    public String getRefundApplyEndDate() {
        return refundApplyEndDate;
    }

    public void setRefundApplyEndDate(String refundApplyEndDate) {
        this.refundApplyEndDate = refundApplyEndDate;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    @Override
    public String toString() {
        return "OrderQuery{" +
                "pageInfo=" + pageInfo +
                ", appkey='" + appkey + '\'' +
                ", orderBeginAt='" + orderBeginAt + '\'' +
                ", orderEndAt='" + orderEndAt + '\'' +
                ", orderId='" + orderId + '\'' +
                ", productName='" + productName + '\'' +
                ", refundApplyBeginDate='" + refundApplyBeginDate + '\'' +
                ", refundApplyEndDate='" + refundApplyEndDate + '\'' +
                ", buyerId='" + buyerId + '\'' +
                '}';
    }
}
