package com.zxp.goods.params;


import lombok.Data;

@Data
/**
 * 提交预占用
 */
public class GoodsPreOccupationDTO {
    private String goodsID;
    private String count;
}
