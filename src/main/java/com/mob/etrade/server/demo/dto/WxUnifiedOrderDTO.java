package com.mob.etrade.server.demo.dto;

import com.mob.etrade.server.demo.pay.wxpay.reqeust.WxUnifiedOrderRequest;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * describe:
 *
 * @author yunkai(xianyi)
 * <p>
 * date 2017/12/12
 */
public class WxUnifiedOrderDTO implements Serializable {

    private static final long serialVersionUID = -3114392532075688852L;
    private String deviceInfo;
    private String signType;
    @NotEmpty
    private String body;
    private String detail;
    private String attach;
    @NotEmpty
    private String outTradeNo;
    private String feeType;
    @NotNull
    private Integer totalFee;
    @NotEmpty
    private String spbillCreateIp;
    private String timeStart;
    private String timeExpire;
    private String goodsTag;
    private String notifyUrl;
    @NotEmpty
    private String tradeType;
    private String limitPay;
    private String sceneInfo;

    public WxUnifiedOrderRequest convertToWxUnifiedOrderRequest() {
        WxUnifiedOrderDTOConvert convert = new WxUnifiedOrderDTOConvert();
        return convert.convert(this);
    }

    private static class WxUnifiedOrderDTOConvert implements Converter<WxUnifiedOrderDTO, WxUnifiedOrderRequest> {
        @Override
        public WxUnifiedOrderRequest convert(WxUnifiedOrderDTO source) {
            WxUnifiedOrderRequest request = new WxUnifiedOrderRequest();
            BeanUtils.copyProperties(source, request);
            return request;
        }
    }

    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
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

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public Integer getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Integer totalFee) {
        this.totalFee = totalFee;
    }

    public String getSpbillCreateIp() {
        return spbillCreateIp;
    }

    public void setSpbillCreateIp(String spbillCreateIp) {
        this.spbillCreateIp = spbillCreateIp;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getTimeExpire() {
        return timeExpire;
    }

    public void setTimeExpire(String timeExpire) {
        this.timeExpire = timeExpire;
    }

    public String getGoodsTag() {
        return goodsTag;
    }

    public void setGoodsTag(String goodsTag) {
        this.goodsTag = goodsTag;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getLimitPay() {
        return limitPay;
    }

    public void setLimitPay(String limitPay) {
        this.limitPay = limitPay;
    }

    public String getSceneInfo() {
        return sceneInfo;
    }

    public void setSceneInfo(String sceneInfo) {
        this.sceneInfo = sceneInfo;
    }

    @Override
    public String toString() {
        return "WxUnifiedOrderDTO{" +
                "deviceInfo='" + deviceInfo + '\'' +
                ", signType='" + signType + '\'' +
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
}
