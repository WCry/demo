package com.lhj.miaosha.controller;

import com.lhj.miaosha.domain.MiaoshaUser;
import com.lhj.miaosha.domain.OrderInfo;
import com.lhj.miaosha.redis.RedisService;
import com.lhj.miaosha.result.CodeMsg;
import com.lhj.miaosha.result.Result;
import com.lhj.miaosha.service.GoodsService;
import com.lhj.miaosha.service.MiaoshaUserService;
import com.lhj.miaosha.service.OrderService;
import com.lhj.miaosha.vo.GoodsVo;
import com.lhj.miaosha.vo.OrderDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ：LHJ
 * @date ：2019/7/29 9:42
 * @description：${description}
 */
@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    MiaoshaUserService userService;

    @Autowired
    RedisService redisService;

    @Autowired
    OrderService orderService;

    @Autowired
    GoodsService goodsService;

    @RequestMapping("/detail")
    @ResponseBody
    public Result<OrderDetailVo> info(Model model, MiaoshaUser user,
                                      @RequestParam("orderId") long orderId) {
        if(user == null) {
            return Result.error(CodeMsg.SESSION_ERROR);
        }
        OrderInfo order = orderService.getOrderById(orderId);
        if(order == null) {
            return Result.error(CodeMsg.ORDER_NOT_EXIST);
        }
        long goodsId = order.getGoodsId();
        GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
        OrderDetailVo vo = new OrderDetailVo();
        vo.setOrder(order);
        vo.setGoods(goods);
        return Result.success(vo);
    }
}
