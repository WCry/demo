//package com.zxp.goods.feign;
//
//
//import com.zxp.service.bean.User;
//import com.zxp.service.redis.GoodsKey;
//import com.zxp.service.redis.RedisService;
//import com.zxp.service.result.Result;
//import com.zxp.service.service.GoodsService;
//import com.zxp.service.service.UserService;
//import com.zxp.service.vo.GoodsDetailVo;
//import com.zxp.service.vo.GoodsVo;
//import org.springframework.context.ApplicationContext;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.util.StringUtils;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.thymeleaf.context.WebContext;
//import org.thymeleaf.spring5.view.ThymeleafViewResolver;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.util.List;
//
//
//@Controller
//@RequestMapping("/goods")
//public class GoodsController {
//
//    final UserService userService;
//
//    final RedisService redisService;
//
//    final GoodsService goodsService;
//
//    final ApplicationContext applicationContext;
//
//    final ThymeleafViewResolver thymeleafViewResolver;
//
//    public GoodsController(UserService userService, RedisService redisService, GoodsService goodsService, ApplicationContext applicationContext, ThymeleafViewResolver thymeleafViewResolver) {
//        this.userService = userService;
//        this.redisService = redisService;
//        this.goodsService = goodsService;
//        this.applicationContext = applicationContext;
//        this.thymeleafViewResolver = thymeleafViewResolver;
//    }
//
//    /**
//     * 商品列表页面
//     * QPS:433
//     * 1000 * 10
//     */
//    @RequestMapping(value = "/to_list", produces = "text/html")
//    @ResponseBody
//    public String list(HttpServletRequest request, HttpServletResponse response, Model model, User user) {
//
//        //取缓存
//        String html = redisService.get(GoodsKey.getGoodsList, "", String.class);
//        if (!StringUtils.isEmpty(html)) {
//            return html;
//        }
//        List<GoodsVo> goodsList = goodsService.listGoodsVo();
//        model.addAttribute("user", user);
//        model.addAttribute("goodsList", goodsList);
//        //手动渲染
//        WebContext wtx = new WebContext(request,response,request.getServletContext(),
//                request.getLocale(),model.asMap());
//
//        html = thymeleafViewResolver.getTemplateEngine().process("goods_list", wtx);
//
//        if (!StringUtils.isEmpty(html)) {
//            redisService.set(GoodsKey.getGoodsList, "", html);
//        }
//        //结果输出
//        return html;
//    }
//
//
//    /**
//     * 商品详情页面
//     */
//    @RequestMapping(value = "/to_detail2/{goodsId}", produces = "text/html")
//    @ResponseBody
//    public String detail2(HttpServletRequest request, HttpServletResponse response, Model model, User user, @PathVariable("goodsId") long goodsId) {
//        model.addAttribute("user", user);
//
//        //取缓存
//        String html = redisService.get(GoodsKey.getGoodsDetail, "" + goodsId, String.class);
//        if (!StringUtils.isEmpty(html)) {
//            return html;
//        }
//
//        //根据id查询商品详情
//        GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
//        model.addAttribute("goods", goods);
//
//        long startTime = goods.getStartDate().getTime();
//        long endTime = goods.getEndDate().getTime();
//        long now = System.currentTimeMillis();
//
//        int seckillStatus = 0;
//        int remainSeconds = 0;
//
//        if (now < startTime) {//秒杀还没开始，倒计时
//            seckillStatus = 0;
//            remainSeconds = (int) ((startTime - now) / 1000);
//        } else if (now > endTime) {//秒杀已经结束
//            seckillStatus = 2;
//            remainSeconds = -1;
//        } else {//秒杀进行中
//            seckillStatus = 1;
//            remainSeconds = 0;
//        }
//        model.addAttribute("seckillStatus", seckillStatus);
//        model.addAttribute("remainSeconds", remainSeconds);
//
//        //手动渲染
//        WebContext wtx = new WebContext(request,response,request.getServletContext(),
//                request.getLocale(),model.asMap());
//        html = thymeleafViewResolver.getTemplateEngine().process("goods_detail", wtx);
//        if (!StringUtils.isEmpty(html)) {
//            redisService.set(GoodsKey.getGoodsDetail, "" + goodsId, html);
//        }
//        return html;
//    }
//
//    /**
//     * 商品详情页面
//     */
//    @RequestMapping(value = "/detail/{goodsId}")
//    @ResponseBody
//    public Result<GoodsDetailVo> detail(HttpServletRequest request, HttpServletResponse response, Model model, User user, @PathVariable("goodsId") long goodsId) {
//
//        //根据id查询商品详情
//        GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
//        model.addAttribute("goods", goods);
//
//        long startTime = goods.getStartDate().getTime();
//        long endTime = goods.getEndDate().getTime();
//        long now = System.currentTimeMillis();
//
//        int seckillStatus = 0;
//        int remainSeconds = 0;
//
//        if (now < startTime) {//秒杀还没开始，倒计时
//            seckillStatus = 0;
//            remainSeconds = (int) ((startTime - now) / 1000);
//        } else if (now > endTime) {//秒杀已经结束
//            seckillStatus = 2;
//            remainSeconds = -1;
//        } else {//秒杀进行中
//            seckillStatus = 1;
//            remainSeconds = 0;
//        }
//        GoodsDetailVo vo = new GoodsDetailVo();
//        vo.setGoods(goods);
//        vo.setUser(user);
//        vo.setRemainSeconds(remainSeconds);
//        vo.setSeckillStatus(seckillStatus);
//
//        return Result.success(vo);
//    }
//}
