package com.mob.etrade.server.demo.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.mob.etrade.server.demo.MobServerClient;
import com.mob.etrade.server.demo.bean.BatchRefund;
import com.mob.etrade.server.demo.bean.MobResult;
import com.mob.etrade.server.demo.bean.OrderQuery;
import com.mob.etrade.server.demo.bean.RefundPage;
import com.mob.etrade.server.demo.service.MobRefundService;
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
public class MobRefundServiceImpl implements MobRefundService {

    @Resource
    private MobServerClient mobServerClient;

    @Override
    public MobResult<RefundPage> queryRefundList(OrderQuery orderQuery) {
        return mobServerClient.postForObject("thirdparty/ertrance/queryRefundList", orderQuery,
                new TypeReference<MobResult<RefundPage>>() {});
    }

    @Override
    public MobResult updateRefundStatus(BatchRefund batchRefund) {
        return mobServerClient.postForObject("thirdparty/ertrance/updateRefundStatus", batchRefund,
                new TypeReference<MobResult>() {});
    }
}
