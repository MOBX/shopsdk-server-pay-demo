package com.mob.etrade.server.demo.controller;

import com.mob.etrade.server.demo.MobException;
import com.mob.etrade.server.demo.bean.CustomResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常handler
 * <p>
 * describe:
 *
 * @author yunkai(xianyi)
 * <p>
 * date 2017/12/20
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(MobException.class)
    public @ResponseBody CustomResult mobExceptionHandler(MobException mobException) {
        LOGGER.error("Error", mobException);
        return new CustomResult( mobException.getMessage());
    }


}
