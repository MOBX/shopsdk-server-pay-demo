package com.mob.etrade.server.demo.controller;

import com.mob.etrade.server.demo.bean.CustomResult;
import com.mob.etrade.server.demo.pay.wxpay.BaseWxResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;

import static com.mob.etrade.server.demo.pay.wxpay.constant.WxPayStateEnum.SUCCESS;

/**
 * describe:
 *
 * @author yunkai(xianyi)
 * <p>
 * date 2017/12/19
 */
public class WxResponseConverter<T extends BaseWxResponse> implements Converter<T, CustomResult<T>> {

    @Override
    public CustomResult<T> convert(T source) {
        if (source == null) {
            return new CustomResult<>("response is null");
        }

        if (SUCCESS.getCode().equals(source.getReturnCode()) && SUCCESS.getCode().equals(source.getResultCode())) {
            return new CustomResult<>(true, source);
        }
        String errMsg = StringUtils.isNotBlank(source.getErrCodeDes())
                ? source.getErrCode() + ": " + source.getErrCodeDes(): source.getReturnMsg();
        return new CustomResult<>(errMsg);
    }
}
