package com.zxp.goods.pojo;


import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 商品信息
 */
@Data
@Table(name = "goods")
@DynamicUpdate
@DynamicInsert
public class Goods {
    @Id
    private Long id;
    private Long available_quantity;
    private Long lock_occupation;
    private Long pre_occupation;
    private String goods_name;
    private String goods_title;
    private String goods_img;
    private String goods_detail;
    private Double goods_price;
    private Integer goods_stock;
}
