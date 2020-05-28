package com.lhj.miaosha.controller;

import com.alibaba.druid.util.StringUtils;
import com.lhj.miaosha.domain.MiaoshaUser;
import com.lhj.miaosha.redis.GoodsKey;
import com.lhj.miaosha.redis.RedisService;
import com.lhj.miaosha.result.Result;
import com.lhj.miaosha.service.GoodsService;
import com.lhj.miaosha.service.MiaoshaUserService;
import com.lhj.miaosha.vo.GoodsDetailVo;
import com.lhj.miaosha.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.context.IWebContext;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author ：LHJ
 * @date ：2019/7/26 18:00
 * @description：${description}
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    MiaoshaUserService userService;

    @Autowired
    RedisService redisService;

    @Autowired
    GoodsService goodsService;

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    ThymeleafViewResolver thymeleafViewResolver;

    @RequestMapping(value = "/to_list", produces = "text/html")
    @ResponseBody
    public String list(HttpServletRequest request, HttpServletResponse response, Model model, MiaoshaUser user) {

        //取缓存
        String html = redisService.get(GoodsKey.getGoodsList, "", String.class);
        if (!StringUtils.isEmpty(html)) {
            return html;
        }

        List<GoodsVo> goodsList = goodsService.listGoodsVo();
        model.addAttribute("user", user);
        model.addAttribute("goodsList", goodsList);

        //手动渲染
        IWebContext ctx =new WebContext(request,response,
                request.getServletContext(),request.getLocale(),model.asMap());
        html = thymeleafViewResolver.getTemplateEngine().process("goods_list", ctx);

        if (!StringUtils.isEmpty(html)) {
            redisService.set(GoodsKey.getGoodsList, "", html);
        }
        //结果输出
        return html;
    }
    /**
     * 商品详情页面
     * 页面静态化的to_detail
     */
    @RequestMapping(value = "/detail/{goodsId}")
    @ResponseBody
    public Result<GoodsDetailVo> detail(Model model,
                                        MiaoshaUser user,
                                        @PathVariable("goodsId") Integer goodsId) {
        System.out.println(goodsId);
        //根据id查询商品详情
        GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
        model.addAttribute("goods", goods);
        long startTime = goods.getStartDate().getTime();
        long endTime = goods.getEndDate().getTime();
        long now = System.currentTimeMillis();

        int miaoshaStatus = 0;
        int remainSeconds = 0;

        if (now < startTime) {//秒杀还没开始，倒计时
            miaoshaStatus = 0;
            remainSeconds = (int) ((startTime - now) / 1000);
        } else if (now > endTime) {//秒杀已经结束
            miaoshaStatus = 2;
            remainSeconds = -1;
        } else {//秒杀进行中
            miaoshaStatus = 1;
            remainSeconds = 0;
        }
        GoodsDetailVo vo = new GoodsDetailVo();
        vo.setGoods(goods);
        vo.setUser(user);
        vo.setRemainSeconds(remainSeconds);
        vo.setMiaoshaStatus(miaoshaStatus);
        return Result.success(vo);
    }

    /**
     * 商品详情页面
     *页面缓存的to_detail
     */
    @RequestMapping(value = "/to_detail/{goodsId}", produces = "text/html")
    @ResponseBody
    public String detail2(HttpServletRequest request, HttpServletResponse response, Model model, MiaoshaUser user, @PathVariable("goodsId") long goodsId) {
        model.addAttribute("user", user);

        //取缓存
        String html = redisService.get(GoodsKey.getGoodsDetail, "" + goodsId, String.class);
        if (!StringUtils.isEmpty(html)) {
            return html;
        }

        //根据id查询商品详情
        GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
        model.addAttribute("goods", goods);

        long startTime = goods.getStartDate().getTime();
        long endTime = goods.getEndDate().getTime();
        long now = System.currentTimeMillis();

        int miaoshaStatus = 0;
        int remainSeconds = 0;

        if (now < startTime) {//秒杀还没开始，倒计时
            miaoshaStatus = 0;
            remainSeconds = (int) ((startTime - now) / 1000);
        } else if (now > endTime) {//秒杀已经结束
            miaoshaStatus = 2;
            remainSeconds = -1;
        } else {//秒杀进行中
            miaoshaStatus = 1;
            remainSeconds = 0;
        }
        model.addAttribute("miaoshaStatus",miaoshaStatus);
        model.addAttribute("remainSeconds",remainSeconds);

        //手动渲染
        IWebContext ctx =new WebContext(request,response,
                request.getServletContext(),request.getLocale(),model.asMap());
        html = thymeleafViewResolver.getTemplateEngine().process("goods_detail", ctx);
        if (!StringUtils.isEmpty(html)) {
            redisService.set(GoodsKey.getGoodsDetail, "" + goodsId, html);
        }
        return html;
    }


}
