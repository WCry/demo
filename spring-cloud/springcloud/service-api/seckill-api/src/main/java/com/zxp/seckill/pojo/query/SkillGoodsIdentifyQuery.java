package com.zxp.seckill.pojo.query;

import com.zxp.seckill.pojo.SecKillGoodBaseInfo;
import com.zxp.seckill.pojo.SecKillGoodIdentify;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * 秒杀物品查询信息
 */
@Data
@ToString
public class SkillGoodsIdentifyQuery implements SecKillGoodIdentify {
    private Long secKillID;
}
