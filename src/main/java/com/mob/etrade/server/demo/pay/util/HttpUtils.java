package com.mob.etrade.server.demo.pay.util;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * describe:
 *
 * @author yunkai(xianyi)
 * <p>
 * date 2017/12/23
 */
public class HttpUtils {



    public static String doPost(String url, String content, String ctype) {
        return doPost(url, content, UTF_8.toString(), ctype, defaultCloseableHttpClient());
    }

    public static String doPost(String url, String content, String ctype, SSLConnectionSocketFactory sslsf) {
        CloseableHttpClient httpClient = HttpClientBuilder.create().setSSLSocketFactory(sslsf).build();

        return doPost(url, content, UTF_8.toString(), ctype, httpClient);
    }

    public static String doPost(String url, String content, String charset, String ctype,CloseableHttpClient closeableHttpClient) {
        HttpPost httpPost = new HttpPost(url);
        charset = charset != null? charset: UTF_8.toString();

        StringEntity stringEntity = new StringEntity(content, charset);

        httpPost.setHeader("Content-type", ctype);
        httpPost.setEntity(stringEntity);

        assert closeableHttpClient != null;
        try (CloseableHttpResponse response = closeableHttpClient.execute(httpPost);){
            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity, charset);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static CloseableHttpClient defaultCloseableHttpClient() {
        return HttpClientBuilder.create().build();
    }
}
