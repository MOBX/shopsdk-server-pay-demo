package com.mob.etrade.server.demo.service;

import com.mob.etrade.server.demo.bean.MobResult;
import com.mob.etrade.server.demo.bean.OrderInfo;
import com.mob.etrade.server.demo.constant.PayStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * describe:
 *
 * @author yunkai(xianyi)
 * <p>
 * date 2017/12/29
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MobOrderServiceTest {

    @Resource
    private MobOrderService mobOrderService;

    @Test
    public void testUpdateOrderStatus() {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setOrderId("111861750046392320");
        orderInfo.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        orderInfo.setPayTicket("4200000021201801037168415881");
        orderInfo.setTotalMoney(1);
        orderInfo.setPaidMoney(1);
        orderInfo.setAppkey("221a0c04f52d4");

        MobResult mobResult = mobOrderService.updateOrderStatus(orderInfo);
        Assert.assertTrue("SUCCESS".equals(mobResult.getMessage()));
    }
}
