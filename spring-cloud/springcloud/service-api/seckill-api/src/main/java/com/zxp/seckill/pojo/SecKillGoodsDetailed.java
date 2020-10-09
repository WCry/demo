package com.zxp.seckill.pojo;

import java.util.Date;

/**
 * 描述物品详细信息
 */
public interface SecKillGoodsDetailed extends SecKillGoodBaseInfo {
    String getGoodsName();

    String getGoodsTitle();

    String getGoodsImg();

    String getGoodsDetail();

    Double getGoodsPrice();

    Integer getGoodsStock();

    Double getSecKillPrice();

    Integer getStockCount();


}
