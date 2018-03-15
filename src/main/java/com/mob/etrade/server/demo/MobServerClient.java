package com.mob.etrade.server.demo;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mob.etrade.server.demo.util.MD5Util;
import com.mob.etrade.server.demo.util.RSAUtil;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * describe:
 *
 * @author yunkai(xianyi)
 * <p>
 * date 2017/12/29
 */
public class MobServerClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(MobServerClient.class);

    private String appkey;
    private String publicKey;
    private String appSecret;
    private String baseUrl;

    public MobServerClient(String appkey, String publicKey, String appSecret, String baseUrl) {
        this.appkey = appkey;
        this.publicKey = publicKey;
        this.appSecret = appSecret;
        this.baseUrl = baseUrl;
    }

    public <T> T postForObject(String url, Object request, TypeReference<T> responseType) {
        ObjectMapper mapper = new ObjectMapper();
        // 1. obj -> Json
        JsonNode jsonNode = mapper.convertValue(request, JsonNode.class);

        LOGGER.info("request body {}", jsonNode.toString());

        HttpPost httpPost = new HttpPost(baseUrl + url);
        //2. 请求头中应有appkey
        httpPost.setHeader("appkey", appkey);

        //3. 请求参数签名
        httpPost.setHeader("mobsign", createSign(jsonNode));

        //4. RSA明文变密文
        String encrypt = RSAUtil.rsaEncrypt(jsonNode.toString(), publicKey, UTF_8.toString());

        StringEntity stringEntity = new StringEntity(encrypt, UTF_8);

        httpPost.setEntity(stringEntity);

        String responseStr = null;
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build();
             CloseableHttpResponse response = httpClient.execute(httpPost)){
            responseStr = EntityUtils.toString(response.getEntity(), UTF_8);
            LOGGER.info("response body {}", responseStr);
            //5. 安全性要求高的接口，这里应该做一个验签
            return mapper.readValue(responseStr, responseType);

        } catch (IOException e) {
            throw new MobApiException(url + " request fail", e);
        }
    }


    /**
     * 将请求参数按字典序排列拼接成字符串
     *
     * @param jsonNode 请求对象
     * @return sign
     */
    private String createSign(JsonNode jsonNode) {

        Iterator<String> fieldNames = jsonNode.fieldNames();

        Map<String, String> sortedMap = new TreeMap<>();
        while (fieldNames.hasNext()) {
            String fieldName = fieldNames.next();
            sortedMap.put(fieldName, jsonNode.path(fieldName).toString());
        }

        return MD5Util.createSign(sortedMap, UTF_8.toString(), appSecret);
    }


}
