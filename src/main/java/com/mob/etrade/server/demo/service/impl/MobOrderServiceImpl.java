package com.mob.etrade.server.demo.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.mob.etrade.server.demo.MobServerClient;
import com.mob.etrade.server.demo.bean.MobResult;
import com.mob.etrade.server.demo.bean.OrderInfo;
import com.mob.etrade.server.demo.service.MobOrderService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * describe:
 *
 * @author yunkai(xianyi)
 * <p>
 * date 2017/12/29
 */
@Component
public class MobOrderServiceImpl implements MobOrderService {
    @Resource
    private MobServerClient mobServerClient;

    @Override
    public MobResult updateOrderStatus(OrderInfo orderInfo) {
        return mobServerClient.postForObject("thirdparty/ertrance/updateOrderStatus",
                orderInfo, new TypeReference<MobResult>() {});
    }

    @Override
    public MobResult<OrderInfo> queryOrderDetail(OrderInfo orderInfo) {
        return mobServerClient.postForObject("thirdparty/ertrance/queryOrderDetail",
                orderInfo, new TypeReference<MobResult<OrderInfo>>() {});
    }
}
