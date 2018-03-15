package com.mob.etrade.server.demo.dto;

import com.mob.etrade.server.demo.pay.wxpay.reqeust.WxUnifiedOrderRequest;
import org.apache.commons.lang3.time.FastDateFormat;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * describe:
 *
 * @author yunkai(xianyi)
 * <p>
 * date 2017/12/13
 */
public class WxUnifiedOrderDTOTest {

    @Test
    public void testConvertToWxUnifiedOrderRequest() {
        WxUnifiedOrderDTO dto = new WxUnifiedOrderDTO();
        dto.setAttach("附加数据，原样返回");
        dto.setSignType("MD5");
        dto.setGoodsTag("tag");
        dto.setLimitPay("no_credit");
        dto.setNotifyUrl("http://www.mob.com/");
        dto.setOutTradeNo("201712130015");
        dto.setSpbillCreateIp("127.0.0.1");
        dto.setTradeType("APP");
        dto.setTimeStart(FastDateFormat.getInstance("yyyyMMddHHmmSS").format(System.currentTimeMillis()));
        dto.setTimeExpire("20171213110000");
        dto.setBody("商品描述交易字段");
        dto.setDeviceInfo("12345678");

        dto.setDetail("商品详细描述");
        dto.setFeeType("CNY");
        dto.setTotalFee(100);

        WxUnifiedOrderRequest request = dto.convertToWxUnifiedOrderRequest();
        assertNotNull(request);

        assertEquals(dto.getTotalFee(), request.getTotalFee());
        assertEquals(dto.getAttach(), request.getAttach());
        assertEquals(dto.getSignType(), request.getSignType());
        assertEquals(dto.getGoodsTag(), request.getGoodsTag());
        assertEquals(dto.getLimitPay(), request.getLimitPay());
        assertEquals(dto.getNotifyUrl(), request.getNotifyUrl());
        assertEquals(dto.getOutTradeNo(), request.getOutTradeNo());
        assertEquals(dto.getSpbillCreateIp(), request.getSpbillCreateIp());
        assertEquals(dto.getTradeType(), request.getTradeType());
        assertEquals(dto.getTimeExpire(), request. getTimeExpire());
        assertEquals(dto.getTimeStart(), request.getTimeStart());
        assertEquals(dto.getBody(), request.getBody());
        assertEquals(dto.getDeviceInfo(), request.getDeviceInfo());
        assertEquals(dto.getDetail(), dto.getDetail());
        assertEquals(dto.getFeeType(), request.getFeeType());
    }
}
