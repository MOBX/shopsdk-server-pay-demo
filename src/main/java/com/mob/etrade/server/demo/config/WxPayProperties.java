package com.mob.etrade.server.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * describe: 微信支付 properties
 *
 * @author yunkai(xianyi)
 * <p>
 * date 2017/12/12
 */
@Configuration
@PropertySource("classpath:wx.properties")
@ConfigurationProperties(prefix = "weixin")
public class WxPayProperties {

    private String appid;
    private String mchid;
    private String secret;
    private String certPath;
    private String baseUrl;
    private String notifyUrl;

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMchid() {
        return mchid;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public void setMchid(String mchid) {
        this.mchid = mchid;
    }

    public String getCertPath() {
        return certPath;
    }

    public void setCertPath(String certPath) {
        this.certPath = certPath;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }
}
