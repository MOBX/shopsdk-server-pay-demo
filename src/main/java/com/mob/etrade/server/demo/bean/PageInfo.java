package com.mob.etrade.server.demo.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * describe: 分页信息
 *
 * @author yunkai(xianyi)
 * <p>
 * date 2017/12/18
 */
@JsonInclude(Include.NON_NULL)
public class PageInfo {
    private static final int DEFAULT_INDEX = 0;
    private static final int DEFAULT_SIZE = 10;

    private Integer pageIndex;
    private Integer pageSize;
    private Integer count;

    public Integer getPageIndex() {
        if (pageIndex == null) {
            return DEFAULT_INDEX;
        }
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        if (pageSize == null) {
            return DEFAULT_SIZE;
        }
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "PageInfo{" +
                "pageIndex=" + pageIndex +
                ", pageSize=" + pageSize +
                ", count=" + count +
                '}';
    }
}
