package com.mob.etrade.server.demo.pay.util;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * describe:
 *
 * @author yunkai(xianyi)
 * <p>
 * date 2017/12/12
 */
public class SignUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(SignUtil.class);

    /**
     * 将bean转换为SortedMap
     * @param bean 待转换的bean 可通过在field或者对应的访问器上添加注解修改key的值
     *             @see JsonProperty#value()
     * @param <T> obj
     * @return key 为string 的sortedMap 即按字典序排列的有序map
     */
    private static <T> SortedMap<String, String> toSortedMap(T bean) {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.convertValue(bean, new TypeReference<TreeMap<String, String>>() {});
    }

    public static <T> String createSign(T bean, String secret) {
        assert bean != null;
        return createSign(toSortedMap(bean), secret);
    }

    public static <T> boolean verifySign(T bean, String secret) {
        try {
            return verifySign(toSortedMap(bean), secret);
        } catch (Exception e) {
            LOGGER.error("verifySign Fail", e);
        }
        return false;
    }

    private static boolean verifySign(SortedMap<String, String> sortedMap, String secret) {
        String sign = sortedMap.remove("sign");
        Objects.requireNonNull(sign);

        String verifySign = createSign(sortedMap, secret);
        return sign.equals(verifySign);
    }

    /**
     * 生成签名
     * @param sortedMap 参数列表（字典序）
     * @param secret 秘钥key
     * @return sign
     */
    public static String createSign(SortedMap<String, String> sortedMap, String secret) {
        if (CollectionUtils.isEmpty(sortedMap)) {
            return StringUtils.EMPTY;
        }
        Objects.requireNonNull(secret);

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entity : sortedMap.entrySet()) {
            if (StringUtils.isNotBlank(entity.getValue())) {
                sb.append(entity.getKey()).append("=").append(entity.getValue()).append("&");
            }
        }
        sb.append("key=").append(secret);
        return byte2hex(encryptMD5(sb.toString()));
    }

    /**
     * @return 32位随机字符串
     */
    public static String createNoneStr() {
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 32);
    }

    /**
     * 生成md5信息摘要
     * @param str 待摘要的文本
     * @return 摘要的16 进制字符串
     * @throws IllegalArgumentException 使用运行时异常包裹检查异常
     * @see java.security.NoSuchAlgorithmException
     */
    public static byte[] encryptMD5(String str) {
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException(e);
        }
        return md5.digest(str.getBytes(UTF_8));
    }

    public static String byte2hex(byte[] bytes) {
        StringBuilder sign = new StringBuilder();

        for(int i = 0; i < bytes.length; ++i) {
            String hex = Integer.toHexString(bytes[i] & 255);
            if (hex.length() == 1) {
                sign.append("0");
            }
            sign.append(hex.toUpperCase());
        }
        return sign.toString();
    }

    /**
     * 生成HmacSHA256信息摘要
     * @param str 待摘要文本
     * @param key 密钥
     * @return HmacSHA256 摘要16进制字符串
     * @throws Exception
     */
    private static String encryptHMACSHA256(String str, String key) throws Exception {
        Mac hmacSHA256 = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(UTF_8), "HmacSHA256");
        hmacSHA256.init(secretKey);
        byte[] array = hmacSHA256.doFinal(str.getBytes(UTF_8));
        return byte2hex(array);
    }


}
