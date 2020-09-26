package com.zxp.seckill.pojo.vo;


import com.zxp.seckill.pojo.SecKillGoodsDetailed;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * 秒杀商品信息
 */
@Data
@ToString
public class SecKillDetailVo implements SecKillGoodsDetailed {
    private Long secKillID;
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
}

