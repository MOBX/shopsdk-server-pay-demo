package com.mob.etrade.server.demo.pay.wxpay.reqeust;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mob.etrade.server.demo.pay.PayRequest;
import com.mob.etrade.server.demo.pay.wxpay.BaseWxReqeust;
import com.mob.etrade.server.demo.pay.wxpay.response.WxRefundResponse;

/**
 * 微信退款请求对象
 * 可选择实现
 * @see PayRequest#check() 方法，并对参数进行校验
 *
 * <p>
 * describe:
 *
 * @author yunkai(xianyi)
 * <p>
 * date 2017/12/14
 */
public class WxRefundRequest extends BaseWxReqeust<WxRefundResponse> {

    private static final long serialVersionUID = 3749862975564966980L;

    /**
     * 微信订单号，支付通知时返回，与outTradeNo二选一不能同时为空
     *
     */
    private String transactionId;
    /**
     * 商户订单号，与outTradeNo二选一。不能同时为空
     */
    private String outTradeNo;
    /**
     * 商户退款单号
     */
    private String outRefundNo;
    /**
     * 订单金额
     */
    private Integer totalFee;
    /**
     * 退款金额
     */
    private Integer refundFee;
    /**
     * 货币种类，默认CNY
     */
    private String refundFeeType;
    /**
     * 退款原因
     */
    private String refundDesc;
    /**
     * 退款资金来源
     */
    private String refundAccount;

    @JsonProperty("transaction_id")
    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
    @JsonProperty("out_trade_no")
    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }
    @JsonProperty("out_refund_no")
    public String getOutRefundNo() {
        return outRefundNo;
    }

    public void setOutRefundNo(String outRefundNo) {
        this.outRefundNo = outRefundNo;
    }
    @JsonProperty("total_fee")
    public Integer getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Integer totalFee) {
        this.totalFee = totalFee;
    }
    @JsonProperty("refund_fee")
    public Integer getRefundFee() {
        return refundFee;
    }

    public void setRefundFee(Integer refundFee) {
        this.refundFee = refundFee;
    }
    @JsonProperty("refund_fee_type")
    public String getRefundFeeType() {
        return refundFeeType;
    }

    public void setRefundFeeType(String refundFeeType) {
        this.refundFeeType = refundFeeType;
    }
    @JsonProperty("refund_desc")
    public String getRefundDesc() {
        return refundDesc;
    }

    public void setRefundDesc(String refundDesc) {
        this.refundDesc = refundDesc;
    }
    @JsonProperty("refund_account")
    public String getRefundAccount() {
        return refundAccount;
    }

    public void setRefundAccount(String refundAccount) {
        this.refundAccount = refundAccount;
    }

    @Override
    public String toString() {
        return "WxRefundRequest{" +
                "transactionId='" + transactionId + '\'' +
                ", outTradeNo='" + outTradeNo + '\'' +
                ", outRefundNo='" + outRefundNo + '\'' +
                ", totalFee=" + totalFee +
                ", refundFee=" + refundFee +
                ", refundFeeType='" + refundFeeType + '\'' +
                ", refundDesc='" + refundDesc + '\'' +
                ", refundAccount='" + refundAccount + '\'' +
                '}';
    }

    @Override
    public Class<WxRefundResponse> getResponseClass() {
        return WxRefundResponse.class;
    }

    @Override
    public String getApiPath() {
        return "/secapi/pay/refund";
    }
}
