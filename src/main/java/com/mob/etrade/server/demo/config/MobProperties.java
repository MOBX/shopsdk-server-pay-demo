package com.mob.etrade.server.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * describe:
 *
 * @author yunkai(xianyi)
 * <p>
 * date 2017/12/20
 */
@Configuration
@PropertySource("classpath:mob.properties")
@ConfigurationProperties(prefix = "mob")
public class MobProperties {

    private String appKey;
    private String appSecret;
    private String baseUrl;
    private String publicKey;

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }
}
