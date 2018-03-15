package com.mob.etrade.server.demo.pay.wxpay;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.io.Serializable;

/**
 * describe: 基础参数
 * @see JsonIgnoreProperties 忽略指定的Override get方法
 * @author yunkai(xianyi)
 * <p>
 * date 2017/12/15
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonRootName("xml")
@JsonIgnoreProperties({"apiPath", "responseClass"})
public abstract class BaseWxReqeust<T extends BaseWxResponse> implements WxPayRequest<T>, Serializable{

    private static final long serialVersionUID = 6663376786513605910L;
    private String appid;
    /**
     * 商户号
     */
    private String mchid;
    /**
     * 随机字符串
     */
    private String nonceStr;
    /**
     * 签名
     */
    private String sign;

    /**
     * 签名类型, HMAC-SHA256和MD5 默认MD5，
     */
    private String signType;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }
    @JsonProperty("mch_id")
    public String getMchid() {
        return mchid;
    }

    public void setMchid(String mchid) {
        this.mchid = mchid;
    }
    @JsonProperty("nonce_str")
    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
    @JsonProperty("sign_type")
    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    /**
     * 可选择实现，
     * @throws IllegalArgumentException 参数校验不通过时抛出
     */
    @Override
    public void check() throws IllegalArgumentException {
        //do some thing
    }
}
