package com.mob.etrade.server.demo.pay.wxpay.response;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.mob.etrade.server.demo.pay.wxpay.BaseWxResponse;

/**
 * 微信统一下单接口response对象
 * <p>
 * describe:
 *
 * @author yunkai(xianyi)
 * <p>
 * date 2017/12/12
 */
public class WxUnifiedOrderResponse extends BaseWxResponse {


    private static final long serialVersionUID = -599246834113948066L;

    @JacksonXmlProperty(localName = "device_info")
    private String deviceInfo;

    @JacksonXmlProperty(localName = "trade_type")
    private String tradeType;
    @JacksonXmlProperty(localName = "prepay_id")
    private String prepayId;

    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getPrepayId() {
        return prepayId;
    }

    public void setPrepayId(String prepayId) {
        this.prepayId = prepayId;
    }

    @Override
    public String toString() {
        return "WxUnifiedOrderResponse{" +
                "deviceInfo='" + deviceInfo + '\'' +
                ", tradeType='" + tradeType + '\'' +
                ", prepayId='" + prepayId + '\'' +
                '}';
    }
}
