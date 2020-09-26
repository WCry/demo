package com.zxp.seckill;

import com.zxp.seckill.pojo.dto.VerificationSecSkillOrder;
import com.zxp.seckill.pojo.query.SkillGoodsIdentifyQuery;
import com.zxp.seckill.pojo.query.SkillGoodsQuery;
import com.zxp.seckill.pojo.vo.SecKillDetailVo;
import com.zxp.user.resoponse.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

public interface SecSkillFeign {
    /**
     * 获取秒杀物品列表，当前直接返回详细信息
     */
    @RequestMapping(value = "/query")
    @ResponseBody
    Result<List<SecKillDetailVo>> querySecSkillDetail(SkillGoodsQuery skillGoodsQuery);

    /**
     * 获取秒杀物品列表，当前直接返回详细信息
     */
    @RequestMapping(value = "/identify")
    @ResponseBody
    Result<SecKillDetailVo> querySecSkillDetailByIdentify(SkillGoodsIdentifyQuery skillGoodsIdentifyQuery);


    /**
     * 秒杀下订单,对于秒杀订单进行验证
     */
    @RequestMapping(value = "/secSkill")
    @ResponseBody
    Result<List<SecKillDetailVo>> secSkillOrder(VerificationSecSkillOrder verificationSecSkillOrder);

}
