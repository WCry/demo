package com.zxp.seckill.pojo;


import java.util.Date;

/**
 * 秒杀商品的基本信息
 */
public interface SecKillGoodBaseInfo extends SecKillGoodIdentify {
     Date getStartDate();

     Date getEndDate();
}

