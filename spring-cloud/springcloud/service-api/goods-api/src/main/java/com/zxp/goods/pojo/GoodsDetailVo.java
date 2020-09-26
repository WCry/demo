package com.zxp.goods.pojo;


import lombok.Data;
import lombok.ToString;

/**
 * 商品信息
 */
@Data
@ToString
public class GoodsDetailVo {
    private Long id;
    private String goodsName;
    private String goodsTitle;
    private String goodsImg;
    private String goodsDetail;
    private Double goodsPrice;
    private Integer goodsStock;
    private Integer stockCount;
    private Integer version;
}

