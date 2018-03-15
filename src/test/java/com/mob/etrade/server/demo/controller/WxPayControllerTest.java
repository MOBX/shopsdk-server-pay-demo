package com.mob.etrade.server.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.mob.etrade.server.demo.config.WxPayProperties;
import com.mob.etrade.server.demo.dto.WxUnifiedOrderDTO;
import com.mob.etrade.server.demo.pay.util.SignUtil;
import com.mob.etrade.server.demo.pay.wxpay.response.WxNotifyResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.matchers.Contains;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.Resource;

import static com.mob.etrade.server.demo.pay.wxpay.constant.WxPayStateEnum.SUCCESS;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 微信支付控制器测试
 * <p>
 * describe:
 *
 * @author yunkai(xianyi)
 * <p>
 * date 2017/12/13
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class WxPayControllerTest {

    @Resource
    private MockMvc mockMvc;
    @Resource
    private WxPayProperties wxPayProperties;

    @Test
    public void testUnifiedorder() throws Exception {
        WxUnifiedOrderDTO dto = new WxUnifiedOrderDTO();
        dto.setBody("女神联盟-游戏充值");
        dto.setOutTradeNo("1415659990");
        dto.setTotalFee(201);
        dto.setSpbillCreateIp("127.0.0.1");
        dto.setTradeType("APP");
        dto.setNotifyUrl("http://127.0.0.1/pay/wx/notify");
        System.out.println(new ObjectMapper().writeValueAsString(dto));
        mockMvc.perform(post("/pay/wx/unifiedorder").accept(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(dto)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andDo(print()).andExpect(content().string(new Contains("true")));
    }

    @Test
    public void testResultNotify() throws Exception {
        WxNotifyResponse wxNotifyResponse = new WxNotifyResponse();
        wxNotifyResponse.setAppid(wxPayProperties.getAppid());
        wxNotifyResponse.setMchid(wxNotifyResponse.getMchid());
        wxNotifyResponse.setNonceStr(SignUtil.createNoneStr());
        wxNotifyResponse.setAttach("test");
        wxNotifyResponse.setBankType("CMC");
        wxNotifyResponse.setOpenid("12345678");
        wxNotifyResponse.setOutTradeNo("111798267992866816");
        wxNotifyResponse.setTotalFee(600);
        wxNotifyResponse.setResultCode(SUCCESS.getCode());
        wxNotifyResponse.setTradeType("APP");
        wxNotifyResponse.setCashFee(0);
        wxNotifyResponse.setTransactionId("wx90892555608600576");
        wxNotifyResponse.setTimeEnd("20171214043010");
        wxNotifyResponse.setSign(SignUtil.createSign(wxNotifyResponse, wxPayProperties.getSecret()));

        mockMvc.perform(post("/pay/wx/notify").accept(MediaType.APPLICATION_XML)
                .content(new XmlMapper().writeValueAsString(wxNotifyResponse)).contentType(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk()).andDo(print())
                .andExpect(content().string(new Contains("SUCCESS")));
    }

    @Test
    public void testResultNofityWithXml() throws Exception {
        String xml = "<xml><appid><![CDATA[wx6c033dfc1026e3cb]]></appid>\n" +
                "<bank_type><![CDATA[CFT]]></bank_type>\n" +
                "<cash_fee><![CDATA[2]]></cash_fee>\n" +
                "<fee_type><![CDATA[CNY]]></fee_type>\n" +
                "<is_subscribe><![CDATA[N]]></is_subscribe>\n" +
                "<mch_id><![CDATA[1493062322]]></mch_id>\n" +
                "<nonce_str><![CDATA[c1133ff33e9d4b87a109b2269aed1e79]]></nonce_str>\n" +
                "<openid><![CDATA[obBmZ0YZTp1HpeCHaUASWe5yL5UI]]></openid>\n" +
                "<out_trade_no><![CDATA[108528370740625408]]></out_trade_no>\n" +
                "<result_code><![CDATA[SUCCESS]]></result_code>\n" +
                "<return_code><![CDATA[SUCCESS]]></return_code>\n" +
                "<sign><![CDATA[A24008C618A28DF966C169487AB3F69D]]></sign>\n" +
                "<time_end><![CDATA[20171225113306]]></time_end>\n" +
                "<total_fee>2</total_fee>\n" +
                "<trade_type><![CDATA[APP]]></trade_type>\n" +
                "<transaction_id><![CDATA[4200000004201712251005072714]]></transaction_id>\n" +
                "</xml>";
        mockMvc.perform(post("/pay/wx/notify").accept(MediaType.TEXT_XML_VALUE).content(xml).contentType(MediaType.TEXT_XML_VALUE))
                .andExpect(status().isOk()).andDo(print()).andExpect(content().string(new Contains("SUCCESS")));
    }

}
