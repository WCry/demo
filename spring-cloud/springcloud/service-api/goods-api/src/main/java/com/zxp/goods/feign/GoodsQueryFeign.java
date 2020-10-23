package com.zxp.goods.feign;


import com.zxp.goods.pojo.vo.GoodsDetailVo;
import com.zxp.resoponse.Result;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



public interface GoodsQueryFeign {

    /**
     * 商品列表页面
     */
    @RequestMapping(value = "/to_list")
    @ResponseBody
    Result<String> list(Model model);

    /**
     * 商品详情页面
     */
    @RequestMapping(value = "/to_detail2/{goodsId}")
    @ResponseBody
    Result<String> detail2(Model model, @PathVariable("goodsId") long goodsId);

    /**
     * 商品详情页面
     */
    @RequestMapping(value = "/detail/{goodsId}")
    @ResponseBody
    Result<GoodsDetailVo> detail(Model model, @PathVariable("goodsId") long goodsId);
}
