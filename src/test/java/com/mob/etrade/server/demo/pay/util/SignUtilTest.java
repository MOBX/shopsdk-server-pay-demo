package com.mob.etrade.server.demo.pay.util;

import com.mob.etrade.server.demo.pay.wxpay.reqeust.WxUnifiedOrderRequest;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 * describe:
 *
 * @author yunkai(xianyi)
 * <p>
 * date 2017/12/13
 */
public class SignUtilTest {

    private static final String SECRET = "testsecret";

    @Test
    public void testCreateNoneStr() {
        Assert.assertEquals(32, SignUtil.createNoneStr().length());
    }

    /**
     * @see com.mob.etrade.server.demo.pay.PayRequest 或者
     * @see com.mob.etrade.server.demo.pay.wxpay.WxPayRequest
     * 如果在上面两个类中新增了getXxx方法，可能导致本测试用例不通过。
     *
     * 请将xxx添加到BaseWxRequet的JsonIgnoreProperties注解中
     * @see com.mob.etrade.server.demo.pay.wxpay.BaseWxReqeust
     * @see com.fasterxml.jackson.annotation.JsonIgnoreProperties
     *
     */
    @Test
    public void testCreateSign() {
        WxUnifiedOrderRequest request =new WxUnifiedOrderRequest();
        request.setNonceStr("6fdd6b037de84d44881f711876ed5c77");
        request.setMchid("123456789");
        request.setAppid("123456789");
        request.setAttach("test");

        String sign = SignUtil.createSign(request, SECRET);

        Assert.assertTrue(StringUtils.isNotBlank(sign));
        Assert.assertEquals("31F35B2D25688B810C99AA2C5BD0CA99", sign);
    }

    @Test
    public void testVerifySign() {
        WxUnifiedOrderRequest request =new WxUnifiedOrderRequest();
        request.setNonceStr(SignUtil.createNoneStr());
        request.setMchid("123456789");
        request.setAppid("123456789");
        request.setAttach("test");
        request.setSign(SignUtil.createSign(request, SECRET));
        Assert.assertTrue(SignUtil.verifySign(request, SECRET));
    }

    @Test
    public void testVerifyDataTamper() {
        WxUnifiedOrderRequest request =new WxUnifiedOrderRequest();
        request.setNonceStr(SignUtil.createNoneStr());
        request.setMchid("123456789");
        request.setAppid("123456789");
        request.setAttach("test");
        request.setSign(SignUtil.createSign(request, SECRET));
        //这里模拟数据篡改
        request.setAttach("test01");
        Assert.assertFalse(SignUtil.verifySign(request, SECRET));
    }
}
