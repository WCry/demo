package com.zxp.goods.feign;


import com.zxp.goods.pojo.GoodsDetailVo;
import com.zxp.user.resoponse.Result;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/goods")
public interface GoodsController {

    /**
     * 商品列表页面
     */
    @RequestMapping(value = "/to_list", produces = "text/html")
    @ResponseBody
    Result<String> list(Model model);

    /**
     * 商品详情页面
     */
    @RequestMapping(value = "/to_detail2/{goodsId}", produces = "text/html")
    @ResponseBody
    Result<String> detail2(Model model, @PathVariable("goodsId") long goodsId);

    /**
     * 商品详情页面
     */
    @RequestMapping(value = "/detail/{goodsId}")
    @ResponseBody
    Result<GoodsDetailVo> detail(Model model, @PathVariable("goodsId") long goodsId);
}
