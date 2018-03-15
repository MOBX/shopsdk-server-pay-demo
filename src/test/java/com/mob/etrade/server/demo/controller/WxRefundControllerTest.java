package com.mob.etrade.server.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mob.etrade.server.demo.bean.Refund;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.matchers.Contains;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.Resource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * describe:
 *
 * @author yunkai(xianyi)
 * <p>
 * date 2017/12/19
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class WxRefundControllerTest {
    @Resource
    private MockMvc mockMvc;

    @Test
    public void testRefund() throws Exception {
        Refund refund = new Refund();
        refund.setOrderId("91221280881942529");
        refund.setRefundCommodityId("91221280881942529");
        refund.setRefundFee(201);
        refund.setOrderPaidMoney(201);
        mockMvc.perform(post("/refund/wx/refund").accept(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(refund)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andDo(print()).andExpect(content().string(new Contains("true")));
    }
}
