package com.zxp.seckill;

import com.zxp.seckill.pojo.SecKillDetailVo;
import com.zxp.seckill.pojo.SkillGoodsQuery;
import com.zxp.user.resoponse.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

public interface SecSkillFeign {
    /**
     * 获取秒杀物品列表，当前直接返回详细信息
     */
    @RequestMapping(value = "/query", produces = "text/html")
    @ResponseBody
    Result<SecKillDetailVo> querySecSkillDetail(SkillGoodsQuery skillGoodsQuery);
}
