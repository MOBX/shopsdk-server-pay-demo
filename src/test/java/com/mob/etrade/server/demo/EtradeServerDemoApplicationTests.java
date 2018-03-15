package com.mob.etrade.server.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.mob.etrade.server.demo.config.WxPayProperties;
import com.mob.etrade.server.demo.pay.wxpay.reqeust.WxUnifiedOrderRequest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EtradeServerDemoApplicationTests {

	@Resource
	private WxPayProperties wxPayProperties;

	@Test
	public void contextLoads() {
		Assert.assertNotNull(wxPayProperties);
		Assert.assertEquals("1493062322", wxPayProperties.getMchid());
		Assert.assertEquals("wx6c033dfc1026e3cb", wxPayProperties.getAppid());
	}

	@Test
	public void testMessageConverters() throws JsonProcessingException {
		WxUnifiedOrderRequest request = new WxUnifiedOrderRequest();
		request.setDeviceInfo("sandboxknew");
		request.setSpbillCreateIp("127.0.0.1");
		String xml = new XmlMapper().writeValueAsString(request);
		Assert.assertFalse(xml.contains("apiPath"));
		Assert.assertFalse(xml.contains("responseClass"));
		Assert.assertTrue(xml.contains("device_info"));
	}

}
