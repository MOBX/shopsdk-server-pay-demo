package com.mob.etrade.server.demo.service;

import com.mob.etrade.server.demo.config.WxPayProperties;
import com.mob.etrade.server.demo.pay.util.SignUtil;
import com.mob.etrade.server.demo.pay.wxpay.BaseWxReqeust;
import com.mob.etrade.server.demo.pay.wxpay.BaseWxResponse;
import com.mob.etrade.server.demo.pay.wxpay.WxPayClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * describe: 基于spring restTemap的client
 *
 * @see com.mob.etrade.server.demo.pay.wxpay.DefaultWxPayClient
 *
 * @author yunkai(xianyi)
 * <p>
 * date 2017/12/23
 */
@Component
public class CustomWxPayClient implements WxPayClient {

    @Resource
    private WxPayProperties wxPayProperties;
    /**
     * 不带证书
     */
    @Resource
    private RestTemplate textPlain2XmlRestTemplate;
    /**
     * 带证书
     */
    @Resource
    private RestTemplate wxCertRestTemplate;

    @Override
    public <T extends BaseWxResponse, P extends BaseWxReqeust<T>> T execute(P wxRequest, boolean useCert) {
        Objects.requireNonNull(wxRequest, "request can not be null");
        addSign(wxRequest);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_XML);
        HttpEntity<P> entity = new HttpEntity<>(wxRequest, httpHeaders);

        if (useCert) {
              return wxCertRestTemplate.postForObject(wxPayProperties.getBaseUrl() + wxRequest.getApiPath(),
                      entity, wxRequest.getResponseClass());
        }
        return textPlain2XmlRestTemplate.postForObject(wxPayProperties.getBaseUrl() + wxRequest.getApiPath(),
                entity, wxRequest.getResponseClass());
    }

    @Override
    public <T extends BaseWxReqeust> void addSign(T wxRequest) {
        wxRequest.setAppid(wxPayProperties.getAppid());
        wxRequest.setMchid(wxPayProperties.getMchid());
        wxRequest.setNonceStr(SignUtil.createNoneStr());
        wxRequest.setSign(SignUtil.createSign(wxRequest, wxPayProperties.getSecret()));
    }
}
