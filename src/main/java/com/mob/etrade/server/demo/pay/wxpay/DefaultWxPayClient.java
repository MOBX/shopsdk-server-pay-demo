package com.mob.etrade.server.demo.pay.wxpay;

import com.mob.etrade.server.demo.pay.util.HttpUtils;
import com.mob.etrade.server.demo.pay.util.SignUtil;
import com.mob.etrade.server.demo.pay.util.XmlMapperUtils;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.ssl.SSLContexts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.Objects;

/**
 * describe: 默认微信支付客户端
 *
 * @author yunkai(xianyi)
 * <p>
 * date 2017/12/23
 */
public class DefaultWxPayClient implements WxPayClient{
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultWxPayClient.class);
    private String serverUrl;
    private String appid;
    private String mchid;
    private String appKey;
    /**
     * 证书密码，如果证书密码同mchid，可以不用设置
     */
    private String certPwd;
    private String certPath;

    private SSLConnectionSocketFactory sslsf;

    public DefaultWxPayClient(String serverUrl, String appid, String mchid, String appKey) {
        this.serverUrl = serverUrl;
        this.appid = appid;
        this.mchid = mchid;
        this.appKey = appKey;
    }

    public DefaultWxPayClient(String serverUrl, String appid, String mchid, String appKey, String certPath) {
        this(serverUrl, appid, mchid, appKey);
        this.certPath = certPath;
    }

    public DefaultWxPayClient(String serverUrl, String appid, String mchid, String appKey, String certPwd, String certPath) {
        this(serverUrl, appid, mchid, appKey);
        this.certPwd = certPwd;
        this.certPath = certPath;
    }

    @Override
    public <T extends BaseWxResponse, P extends BaseWxReqeust<T>> T execute(P wxRequest, boolean useCert) {
        Objects.requireNonNull(wxRequest, "wechat pay request obj can not be null");
        addSign(wxRequest);
        String url = serverUrl + wxRequest.getApiPath();
        String requestStr = XmlMapperUtils.toXml(wxRequest);
        String resp;
        if (useCert) {
            resp = HttpUtils.doPost(url, requestStr, "text/xml;charset=utf-8", instanceSSLConnectionSocketFactory());
        } else {
            resp = HttpUtils.doPost(url, requestStr, "text/xml;charset=utf-8");
        }
        return XmlMapperUtils.toBean(resp, wxRequest.getResponseClass());
    }

    @Override
    public <P extends BaseWxReqeust> void addSign(P wxRequest) {
        wxRequest.setAppid(appid);
        wxRequest.setMchid(mchid);
        wxRequest.setNonceStr(SignUtil.createNoneStr());
        wxRequest.setSign(SignUtil.createSign(wxRequest, appKey));
    }




    private SSLConnectionSocketFactory instanceSSLConnectionSocketFactory() {
        if (sslsf == null) {
            Objects.requireNonNull(certPath, "cert path can not be null");
            String certPassword = certPwd != null? certPwd: mchid;
            KeyStore keyStore = null;
            try {
                keyStore = loadFrom(certPath, certPassword);
                SSLContext sslContext = SSLContexts.custom().loadKeyMaterial(keyStore, certPassword.toCharArray()).build();
                sslsf = new SSLConnectionSocketFactory(
                        sslContext, new String[] {"TLSv1"}, null,
                        SSLConnectionSocketFactory.getDefaultHostnameVerifier());
            } catch (Exception e) {
                throw new IllegalArgumentException("Instance SSLConnectionSocketFactory Fail", e);
            }
        }

        return sslsf;
    }

    private KeyStore loadFrom(String certPath, String certPwd) throws KeyStoreException, IOException, CertificateException, NoSuchAlgorithmException {
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        try (FileInputStream inputStream = new FileInputStream(certPath)) {
            keyStore.load(inputStream, certPwd.toCharArray());
        }
        LOGGER.debug("load cert from {}", certPath);
        return keyStore;
    }
}
