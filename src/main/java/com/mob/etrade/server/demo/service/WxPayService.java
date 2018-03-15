package com.mob.etrade.server.demo.service;

import com.mob.etrade.server.demo.pay.wxpay.reqeust.WxRefundRequest;
import com.mob.etrade.server.demo.pay.wxpay.reqeust.WxUnifiedOrderRequest;
import com.mob.etrade.server.demo.pay.wxpay.response.WxRefundResponse;
import com.mob.etrade.server.demo.pay.wxpay.response.WxUnifiedOrderResponse;

/**
 * 微信支付Service
 *
 * @author xianyi(max)
 * @describe:
 * @date 2017/12/14
 */
public interface WxPayService {

    /**
     * 微信统一下单接口
     * 微信预支付订单（prepay_id）在两小时内有效，接口通过spring cache实现缓存管理
     *
     * 示例中使用本地缓存ehcache，可方便的替换为redis，membercache等分布式实现
     * @param wxUnifiedOrderRequest 统一下单请求对象
     * @return 统一下单反馈对象
     */
    WxUnifiedOrderResponse unifiedOrder(WxUnifiedOrderRequest wxUnifiedOrderRequest);

    /**
     * 微信退款接口
     * @param wxRefundRequest 退款请求对象
     * @return 退款反馈对象
     */
    WxRefundResponse refund(WxRefundRequest wxRefundRequest);

}
