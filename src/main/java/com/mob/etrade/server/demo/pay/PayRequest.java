package com.mob.etrade.server.demo.pay;

/**
 * @author xianyi(max)
 * @describe:
 * @date 2017/12/16
 */
public interface PayRequest<T> {

    /**
     * 获取返回类的Class对象
     * @return Class
     */
    Class<T> getResponseClass();

    /**
     * 请求参数校验
     * @throws IllegalArgumentException 参数不正确时抛出
     */
    void check() throws IllegalArgumentException;

    /**
     * 获取Api路径（不包含基础url）
     * @return eg: /secapi/pay/refund
     */
    String getApiPath();
}
