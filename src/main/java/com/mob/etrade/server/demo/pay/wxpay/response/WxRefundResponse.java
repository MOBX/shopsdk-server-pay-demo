package com.mob.etrade.server.demo.pay.wxpay.response;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.mob.etrade.server.demo.pay.wxpay.BaseWxResponse;

/**
 * 微信退款response
 * <p>
 * describe:
 *
 * @author yunkai(xianyi)
 * <p>
 * date 2017/12/14
 */
public class WxRefundResponse extends BaseWxResponse {
    private static final long serialVersionUID = -6772202978866068614L;
    /**
     * 微信订单号，支付通知时返回，与outTradeNo二选一
     *
     */
    private String transactionId;
    /**
     * 商户订单号，与outTradeNo二选一
     */
    private String outTradeNo;
    /**
     * 商户退款单号
     */
    private String outRefundNo;
    /**
     * 微信退款单号
     */
    private String refundId;
    /**
     * 退款金额,可以做部分退款
     */
    private Integer refundFee;
    /**
     * 应结退款金额 去掉非充值代金券退款金额后的退款金额，退款金额=申请退款金额-非充值代金券退款金额，退款金额<=申请退款金额
     */
    private Integer settlementRefundFee;
    /**
     * 标价金额
     */
    private Integer totalFee;
    /**
     * 标价币种
     */
    private String feeType;
    /**
     * 现金支付金额
     */
    private Integer cashFee;
    /**
     * 现金支付币种, 默认CNY
     */
    private String cashFeeType;
    /**
     * 现金退款金额
     */
    private Integer cashRefundFee;
    /**
     * 退款代金券使用数量
     */
    private Integer couponRefundCount;

    @JacksonXmlProperty(localName = "transaction_id")
    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
    @JacksonXmlProperty(localName = "out_trade_no")
    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }
    @JacksonXmlProperty(localName = "out_refund_no")
    public String getOutRefundNo() {
        return outRefundNo;
    }

    public void setOutRefundNo(String outRefundNo) {
        this.outRefundNo = outRefundNo;
    }
    @JacksonXmlProperty(localName = "refund_id")
    public String getRefundId() {
        return refundId;
    }

    public void setRefundId(String refundId) {
        this.refundId = refundId;
    }
    @JacksonXmlProperty(localName = "refund_fee")
    public Integer getRefundFee() {
        return refundFee;
    }

    public void setRefundFee(Integer refundFee) {
        this.refundFee = refundFee;
    }
    @JacksonXmlProperty(localName = "settlement_total_fee")
    public Integer getSettlementRefundFee() {
        return settlementRefundFee;
    }

    public void setSettlementRefundFee(Integer settlementRefundFee) {
        this.settlementRefundFee = settlementRefundFee;
    }
    @JacksonXmlProperty(localName = "total_fee")
    public Integer getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Integer totalFee) {
        this.totalFee = totalFee;
    }
    @JacksonXmlProperty(localName = "fee_type")
    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }
    @JacksonXmlProperty(localName = "cash_fee")
    public Integer getCashFee() {
        return cashFee;
    }

    public void setCashFee(Integer cashFee) {
        this.cashFee = cashFee;
    }
    @JacksonXmlProperty(localName = "cash_fee_type")
    public String getCashFeeType() {
        return cashFeeType;
    }

    public void setCashFeeType(String cashFeeType) {
        this.cashFeeType = cashFeeType;
    }
    @JacksonXmlProperty(localName = "cash_refund_fee")
    public Integer getCashRefundFee() {
        return cashRefundFee;
    }

    public void setCashRefundFee(Integer cashRefundFee) {
        this.cashRefundFee = cashRefundFee;
    }
    @JacksonXmlProperty(localName = "coupon_refund_count")
    public Integer getCouponRefundCount() {
        return couponRefundCount;
    }

    public void setCouponRefundCount(Integer couponRefundCount) {
        this.couponRefundCount = couponRefundCount;
    }

    @Override
    public String toString() {
        return "WxRefundResponse{" +
                "transactionId='" + transactionId + '\'' +
                ", outTradeNo='" + outTradeNo + '\'' +
                ", outRefundNo='" + outRefundNo + '\'' +
                ", refundId='" + refundId + '\'' +
                ", refundFee=" + refundFee +
                ", settlementRefundFee=" + settlementRefundFee +
                ", totalFee=" + totalFee +
                ", feeType='" + feeType + '\'' +
                ", cashFee=" + cashFee +
                ", cashFeeType='" + cashFeeType + '\'' +
                ", cashRefundFee=" + cashRefundFee +
                ", couponRefundCount=" + couponRefundCount +
                '}';
    }
}
