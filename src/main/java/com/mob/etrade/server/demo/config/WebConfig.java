package com.mob.etrade.server.demo.config;

import com.mob.etrade.server.demo.MobServerClient;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

import javax.annotation.Resource;
import javax.net.ssl.SSLContext;
import java.io.FileInputStream;
import java.security.KeyStore;
import java.util.Collections;

/**
 * describe: spring 内置日志filter
 *
 * @author yunkai(xianyi)
 * <p>
 * date 2017/12/24
 */
@Configuration
public class WebConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebConfig.class);

    @Resource
    private WxPayProperties wxPayProperties;
    @Resource
    private MobProperties mobProperties;

    @Bean
    public CommonsRequestLoggingFilter logFilter() {
        CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
        filter.setIncludeQueryString(true);
        filter.setIncludePayload(true);
        filter.setMaxPayloadLength(10000);
        filter.setIncludeHeaders(false);
        filter.setAfterMessagePrefix("REQUEST DATA : ");
        return filter;
    }

    @Bean
    public MobServerClient mobServerClient() {
        return new MobServerClient(mobProperties.getAppKey(), mobProperties.getPublicKey(),
                mobProperties.getAppSecret(), mobProperties.getBaseUrl());
    }

    @Bean
    public RestTemplate textPlain2XmlRestTemplate(MappingJackson2XmlHttpMessageConverter textPlainXmlConverter) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(textPlainXmlConverter);
        return restTemplate;
    }

    /**
     * 需要证书加密的https请求client
     * @param textPlainXmlConverter 添加对content-type:plain/text 类型的实际内容为xml的解析
     * @return restTemplate
     * @throws Exception 加密异常
     */
    @Bean
    @Lazy
    public RestTemplate wxCertRestTemplate(MappingJackson2XmlHttpMessageConverter textPlainXmlConverter) throws Exception {
        KeyStore keyStore = loadFrom(wxPayProperties.getCertPath(), wxPayProperties.getMchid());
        ClientHttpRequestFactory factory = createClientHttpRequestFactory(keyStore, wxPayProperties.getMchid());
        RestTemplate restTemplate = new RestTemplate(factory);
        restTemplate.getMessageConverters().add(textPlainXmlConverter);
        return restTemplate;
    }

    private static KeyStore loadFrom(String fileName, String password) throws Exception {
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        try(FileInputStream inputStream = new FileInputStream(fileName)) {
            keyStore.load(inputStream, password.toCharArray());
        }
        LOGGER.debug("load cert from {}", fileName);
        return keyStore;
    }

    private static ClientHttpRequestFactory createClientHttpRequestFactory(KeyStore keyStore, String pwd) throws Exception {
        SSLContext sslcontext = SSLContexts.custom()
                .loadKeyMaterial(keyStore, pwd.toCharArray()).build();
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                sslcontext, new String[]{"TLSv1"}, null,
                SSLConnectionSocketFactory.getDefaultHostnameVerifier());
        CloseableHttpClient httpclient = HttpClients.custom()
                .setSSLSocketFactory(sslsf).build();

        return new HttpComponentsClientHttpRequestFactory(httpclient);
    }

    /**
     * 添加对请求头 content-type:text/plain
     * 实际内容为xml的解析
     * @return
     */
    @Bean
    public MappingJackson2XmlHttpMessageConverter textPlainXmlConverter() {
        MappingJackson2XmlHttpMessageConverter converter = new MappingJackson2XmlHttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.TEXT_PLAIN));
        return converter;
    }
}
