package com.zxp.goods.pojo;


import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * 商品信息
 * 比如内存大小
 */
@Data
@ToString
public class MobileSku {
    private Long id;
    private String goodsName;
    private String goodsTitle;
    private String goodsImg;
    private String goodsDetail;
    private Double goodsPrice;
    private Integer goodsStock;
    private Double secKillPrice;
    private Integer stockCount;
    private Date startDate;
    private Date endDate;
    private Integer version;

    public Integer getStockCount() {
        return stockCount;
    }

    public void setStockCount(Integer stockCount) {
        this.stockCount = stockCount;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Double getSecKillPrice() {
        return secKillPrice;
    }

    public void setSecKillPrice(Double secKillPrice) {
        this.secKillPrice = secKillPrice;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}

