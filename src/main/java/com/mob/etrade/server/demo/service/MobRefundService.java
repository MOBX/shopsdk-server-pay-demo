package com.mob.etrade.server.demo.service;

import com.mob.etrade.server.demo.bean.BatchRefund;
import com.mob.etrade.server.demo.bean.MobResult;
import com.mob.etrade.server.demo.bean.OrderQuery;
import com.mob.etrade.server.demo.bean.RefundPage;

/**
 * mob 退款相关服务
 * @author yunkai(xianyi)
 * describe: 退款流程相关接口
 * @date 2017/12/29
 */
public interface MobRefundService {

    /**
     * 查询退款列表
     * 开发者可通过该接口，从mob获取待退款订单
     * @param orderQuery 订单查询条件
     * @return 带分页信息的退款列表
     */
    MobResult<RefundPage> queryRefundList(OrderQuery orderQuery);

    /**
     * 更新退款状态
     * @param batchRefund 批量退款
     * @return 退款状态
     */
    MobResult updateRefundStatus(BatchRefund batchRefund);
}
