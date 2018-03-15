package com.mob.etrade.server.demo.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import java.util.List;

/**
 * describe:
 *
 * @author yunkai(xianyi)
 * <p>
 * date 2017/12/18
 */
@JsonInclude(Include.NON_NULL)
public class RefundPage {

    private List<Refund> list;
    @JsonUnwrapped
    private PageInfo pageInfo;

    public List<Refund> getList() {
        return list;
    }

    public void setList(List<Refund> list) {
        this.list = list;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    @Override
    public String toString() {
        return "RefundPage{" +
                "list=" + list +
                ", pageInfo=" + pageInfo +
                '}';
    }
}
