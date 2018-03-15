package com.mob.etrade.server.demo.pay.wxpay.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mob.etrade.server.demo.pay.wxpay.BaseWxResponse;

/**
 * describe: 微信支付通知返回对象
 *
 * @author yunkai(xianyi)
 * <p>
 * date 2017/12/14
 */

public class WxNotifyResponse extends BaseWxResponse {
    private static final long serialVersionUID = 1346951469487570214L;
    /**
     * 设备号
     */
    private String deviceInfo;
    /**
     * 用户标识
     */
    private String openid;
    /**
     * 是否关注公众账号
     */
    private String subscribe;
    /**
     * 交易类型
     */
    private String tradeType;
    /**
     * 付款银行
     */
    private String bankType;
    /**
     * 总金额
     */
    private Integer totalFee;
    /**
     * 货币种类
     */
    private String feeType;
    /**
     * 现金支付金额
     */
    private Integer cashFee;
    /**
     * 现金支付货币类型
     */
    private String cashFeeType;
    /**
     * 代金券，立减优惠金额
     */
    private Integer couponFee;
    /**
     * 代金券，立减优惠使用数量
     */
    private Integer couponCount;
    /**
     * 微信支付订单号
     */
    private String transactionId;
    /**
     * 商户订单号
     */
    private String outTradeNo;
    /**
     * 商家数据包，统一下单接口提交数据原样返回
     */
    private String attach;
    /**
     * 支付完成时间，格式：yyyyMMddHHmmss
     */
    private String timeEnd;

    @JsonProperty("device_info")
    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    @JsonProperty("is_subscribe")
    public String getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(String subscribe) {
        this.subscribe = subscribe;
    }

    @JsonProperty("trade_type")
    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    @JsonProperty("bank_type")
    public String getBankType() {
        return bankType;
    }

    public void setBankType(String bankType) {
        this.bankType = bankType;
    }

    @JsonProperty("total_fee")
    public Integer getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Integer totalFee) {
        this.totalFee = totalFee;
    }

    @JsonProperty("fee_type")
    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    @JsonProperty("cash_fee")
    public Integer getCashFee() {
        return cashFee;
    }

    public void setCashFee(Integer cashFee) {
        this.cashFee = cashFee;
    }

    @JsonProperty("cash_fee_type")
    public String getCashFeeType() {
        return cashFeeType;
    }

    public void setCashFeeType(String cashFeeType) {
        this.cashFeeType = cashFeeType;
    }

    @JsonProperty("coupon_fee")
    public Integer getCouponFee() {
        return couponFee;
    }

    public void setCouponFee(Integer couponFee) {
        this.couponFee = couponFee;
    }

    @JsonProperty("coupon_count")
    public Integer getCouponCount() {
        return couponCount;
    }

    public void setCouponCount(Integer couponCount) {
        this.couponCount = couponCount;
    }

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

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    @JsonProperty("time_end")
    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    @Override
    public String toString() {
        return "WxNotifyResponse{" +
                "deviceInfo='" + deviceInfo + '\'' +
                ", openid='" + openid + '\'' +
                ", subscribe='" + subscribe + '\'' +
                ", tradeType='" + tradeType + '\'' +
                ", bankType='" + bankType + '\'' +
                ", totalFee=" + totalFee +
                ", feeType='" + feeType + '\'' +
                ", cashFee=" + cashFee +
                ", cashFeeType='" + cashFeeType + '\'' +
                ", couponFee=" + couponFee +
                ", couponCount=" + couponCount +
                ", transactionId='" + transactionId + '\'' +
                ", outTradeNo='" + outTradeNo + '\'' +
                ", attach='" + attach + '\'' +
                ", timeEnd='" + timeEnd + '\'' +
                '}';
    }
}
