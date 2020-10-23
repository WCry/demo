package com.zxp.goods.feign;


import com.zxp.goods.pojo.vo.GoodsDetailVo;
import com.zxp.resoponse.Result;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


public interface GoodOperateFeign {

    /**
     * 增加商品信息
     */
    @RequestMapping(value = "/detail/add")
    @ResponseBody
    Result<GoodsDetailVo> addGoods(Model model, @PathVariable("goodsId") long goodsId);

    /**
     * 删除商品信息
     */
    @RequestMapping(value = "/detail/delete")
    @ResponseBody
    Result<GoodsDetailVo> deleteGoods(Model model, @PathVariable("goodsId") long goodsId);
}
