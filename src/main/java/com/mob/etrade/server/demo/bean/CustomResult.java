package com.mob.etrade.server.demo.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.io.Serializable;

/**
 * describe:
 *
 * @author yunkai(xianyi)
 * <p>
 * date 2017/12/13
 */
@JsonInclude(Include.NON_NULL)
public class CustomResult<T> implements Serializable {

    private boolean success;
    private String errMsg;
    private T data;

    private PageInfo pageInfo;

    public CustomResult(boolean success) {
        this.success = success;
    }

    public CustomResult(String errMsg) {
        this.errMsg = errMsg;
    }

    public CustomResult(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public CustomResult(boolean success, String errMsg, T data) {
        this.success = success;
        this.errMsg = errMsg;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    @Override
    public String toString() {
        return "CustomResult{" +
                "success=" + success +
                ", errMsg='" + errMsg + '\'' +
                ", data=" + data +
                ", pageInfo=" + pageInfo +
                '}';
    }
}
