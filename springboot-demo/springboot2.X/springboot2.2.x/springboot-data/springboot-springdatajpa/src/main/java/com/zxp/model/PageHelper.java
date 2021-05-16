package com.zxp.model;


import javax.persistence.Transient;

/**
 * @author ChangLiang
 * @date 2019/5/29
 */
public class PageHelper extends EntityAuto {


    @Transient
    private Integer pageNum;

    @Transient
    private Integer pageSize;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
