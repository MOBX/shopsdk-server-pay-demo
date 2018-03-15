package com.mob.etrade.server.demo.service;

import com.mob.etrade.server.demo.bean.MobResult;
import com.mob.etrade.server.demo.bean.OrderInfo;

/**
 * mob订单服务
 *
 * @author yunkai(xianyi)
 * @describe:
 * @date 2017/12/29
 */
public interface MobOrderService {

    /**
     * 更新订单状态
     * @param orderInfo 订单信息
     * @return 更新结果
     */
    MobResult updateOrderStatus(OrderInfo orderInfo);
    /**
     * 查询订单详情
     * @param orderInfo 订单查询信息
     * @return 订单详情
     */
    MobResult<OrderInfo> queryOrderDetail(OrderInfo orderInfo);
}
