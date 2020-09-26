package com.zxp.seckill.pojo;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * 秒杀物品查询信息
 */
@Data
@ToString
public class SkillGoodsQuery implements SecKillGoodBaseInfo{
    private Date startDate;
    private Date endDate;
    private Long secKillID;
}
