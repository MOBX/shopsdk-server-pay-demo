package com.mob.etrade.server.demo.pay.wxpay.reqeust;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mob.etrade.server.demo.pay.wxpay.BaseWxReqeust;
import com.mob.etrade.server.demo.pay.wxpay.response.WxUnifiedOrderResponse;

/**
 * 微信统一下单对象
 * <p>
 * describe:
 *
 * @author yunkai(xianyi)
 * <p>
 * date 2017/12/12
 */
public class WxUnifiedOrderRequest extends BaseWxReqeust<WxUnifiedOrderResponse> {

    private static final long serialVersionUID = -4232256426954692689L;

    private String deviceInfo;
    private String body;
    private String detail;
    private String attach;
    private String outTradeNo;

    private String feeType;

    private Integer totalFee;

    private String spbillCreateIp;

    private String timeStart;

    private String timeExpire;

    private String goodsTag;

    private String notifyUrl;

    private String tradeType;

    private String limitPay;

    private String sceneInfo;

    @JsonProperty("device_info")
    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    @JsonProperty("out_trade_no")
    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    @JsonProperty("fee_type")
    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    @JsonProperty("total_fee")
    public Integer getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Integer totalFee) {
        this.totalFee = totalFee;
    }

    @JsonProperty("spbill_create_ip")
    public String getSpbillCreateIp() {
        return spbillCreateIp;
    }

    public void setSpbillCreateIp(String spbillCreateIp) {
        this.spbillCreateIp = spbillCreateIp;
    }

    @JsonProperty("time_start")
    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    @JsonProperty("time_expire")
    public String getTimeExpire() {
        return timeExpire;
    }

    public void setTimeExpire(String timeExpire) {
        this.timeExpire = timeExpire;
    }

    @JsonProperty("goods_tag")
    public String getGoodsTag() {
        return goodsTag;
    }

    public void setGoodsTag(String goodsTag) {
        this.goodsTag = goodsTag;
    }

    @JsonProperty("notify_url")
    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    @JsonProperty("trade_type")
    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    @JsonProperty("limit_pay")
    public String getLimitPay() {
        return limitPay;
    }

    public void setLimitPay(String limitPay) {
        this.limitPay = limitPay;
    }

    @JsonProperty("scene_info")
    public String getSceneInfo() {
        return sceneInfo;
    }

    public void setSceneInfo(String sceneInfo) {
        this.sceneInfo = sceneInfo;
    }

    @Override
    public String toString() {
        return "WxUnifiedOrderRequest{" +
                "deviceInfo='" + deviceInfo + '\'' +
                ", body='" + body + '\'' +
                ", detail='" + detail + '\'' +
                ", attach='" + attach + '\'' +
                ", outTradeNo='" + outTradeNo + '\'' +
                ", feeType='" + feeType + '\'' +
                ", totalFee=" + totalFee +
                ", spbillCreateIp='" + spbillCreateIp + '\'' +
                ", timeStart='" + timeStart + '\'' +
                ", timeExpire='" + timeExpire + '\'' +
                ", goodsTag='" + goodsTag + '\'' +
                ", notifyUrl='" + notifyUrl + '\'' +
                ", tradeType='" + tradeType + '\'' +
                ", limitPay='" + limitPay + '\'' +
                ", sceneInfo='" + sceneInfo + '\'' +
                '}';
    }

    @Override
    public Class<WxUnifiedOrderResponse> getResponseClass() {
        return WxUnifiedOrderResponse.class;
    }

    @Override
    public String getApiPath() {
        return "/pay/unifiedorder";
    }
}
