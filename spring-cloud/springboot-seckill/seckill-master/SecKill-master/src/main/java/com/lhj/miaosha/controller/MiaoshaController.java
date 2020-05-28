package com.lhj.miaosha.controller;

import com.lhj.miaosha.access.AccessLimit;
import com.lhj.miaosha.domain.MiaoshaOrder;
import com.lhj.miaosha.domain.MiaoshaUser;
import com.lhj.miaosha.domain.OrderInfo;
import com.lhj.miaosha.rabbitmq.MQSender;
import com.lhj.miaosha.rabbitmq.MiaoshaMessage;
import com.lhj.miaosha.redis.AccessKey;
import com.lhj.miaosha.redis.GoodsKey;
import com.lhj.miaosha.redis.RedisService;
import com.lhj.miaosha.result.CodeMsg;
import com.lhj.miaosha.result.Result;
import com.lhj.miaosha.service.GoodsService;
import com.lhj.miaosha.service.MiaoshaService;
import com.lhj.miaosha.service.MiaoshaUserService;
import com.lhj.miaosha.service.OrderService;
import com.lhj.miaosha.vo.GoodsVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ：LHJ
 * @date ：2019/7/26 18:00
 * @description：${description}
 */
@Controller
@RequestMapping("/miaosha")
public class MiaoshaController implements InitializingBean {
    @Autowired
    MiaoshaUserService userService;

    @Autowired
    RedisService redisService;

    @Autowired
    GoodsService goodsService;

    @Autowired
    OrderService orderService;

    @Autowired
    MiaoshaService miaoshaService;

    @Autowired
    MQSender sender;

    private Map<Long,Boolean> localOverMap = new HashMap();

    private static Logger logger = LoggerFactory.getLogger(MiaoshaController.class);
    static int time = 0;

    /**
     * 系统初始化时，加载秒杀商品库存到redis和本地内存
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        List<GoodsVo> goodList = goodsService.listGoodsVo();
        if(goodList == null){
            return ;
        }
        for(GoodsVo goodsVo : goodList){
            redisService.set(GoodsKey.getMiaoshaGoodsStock,"" + goodsVo.getId(), goodsVo.getStockCount());
            if(goodsVo.getStockCount() > 0) {
                localOverMap.put(goodsVo.getId(), false);
            }else{
                localOverMap.put(goodsVo.getId(), true);
            }
        }
    }

    /**
     * 1、GET幂等,服务端获取数据，无论调用多少次结果都一样
     * 2、POST，向服务端提交数据，不是幂等
     *
     * RabbitMq实现把同步下单改为异步下单
     */

    @RequestMapping(value = "/{path}/do_miaosha", method = RequestMethod.POST)
    @ResponseBody
    public Result<Integer> miaosha(Model model, MiaoshaUser user,
                                   @RequestParam("goodsId")long goodsId,
                                   @PathVariable("path")String path) {

        if(user == null){
            return Result.error(CodeMsg.SESSION_ERROR);//return "login";
        }
        model.addAttribute("user", user);

        //验证path
        boolean check = miaoshaService.checkPath(user, goodsId, path);
        if(!check){
            return Result.error(CodeMsg.REQUEST_ILLEGAL);
        }
        //内存标记，减少redis访问
        boolean isOver = localOverMap.get(goodsId);
        if(isOver){
            return Result.error(CodeMsg.MIAOSHA_OVER);
        }
        //reids预减库存
        long stock = redisService.decr(GoodsKey.getMiaoshaGoodsStock,""+goodsId);
        if(stock < 0){
            localOverMap.put(goodsId,true);
            return Result.error(CodeMsg.MIAOSHA_OVER);
        }
        logger.info("判断是否重复秒杀次数："+ time++);
        //判断是否重复秒杀到
        MiaoshaOrder order = orderService.getMiaoshaOrderByUserIdGoodsId(user.getId(),goodsId);
        if(order != null){
            //重复秒杀时需要把预减的库存加回去，并重置localOverMap
            redisService.incr(GoodsKey.getMiaoshaGoodsStock,"" + goodsId);
            localOverMap.put(goodsId,false);
            return Result.error(CodeMsg.REPEATE_MIAOSHA);
        }

        //入队
        MiaoshaMessage message = new MiaoshaMessage();
        message.setUser(user);
        message.setGoodsId(goodsId);
        sender.sendMiaoshaMessage(message);
        return Result.success(0);            //排队中
    }
    /**
     * @return orderId：成功
     * @return -1：秒杀失败
     * @return 0： 排队中
     */
    @RequestMapping(value = "/result", method = RequestMethod.GET)
    @ResponseBody
    public Result<Long> miaoshaResult(Model model, MiaoshaUser user,
                                      @RequestParam("goodsId") long goodsId) {
        model.addAttribute("user", user);
        if (user == null) {
            return Result.error(CodeMsg.SESSION_ERROR);
        }
        long orderId = miaoshaService.getMiaoshaResult(user.getId(), goodsId);
        return Result.success(orderId);
    }

    @AccessLimit(seconds=5, maxCount=5, needLogin=true)
    @RequestMapping(value="/path", method=RequestMethod.GET)
    @ResponseBody
    public Result<String> getMiaoshaPath(HttpServletRequest request, MiaoshaUser user,
                                         @RequestParam("goodsId")long goodsId,
                                         @RequestParam(value="verifyCode", defaultValue="0")int verifyCode
    ) {
        if(user == null) {
            return Result.error(CodeMsg.SESSION_ERROR);
        }
        String path = miaoshaService.createMiaoshaPath(user,goodsId);
        boolean check = miaoshaService.checkVerifyCode(user, goodsId, verifyCode);
        if(!check) {
            return Result.error(CodeMsg.REQUEST_ILLEGAL);
        }
        return Result.success(path);
    }

    @RequestMapping(value="/verifyCode", method=RequestMethod.GET)
    @ResponseBody
    public Result<String> getMiaoshaVerifyCode(HttpServletResponse response, MiaoshaUser user,
                                              @RequestParam("goodsId")long goodsId) {
        if(user == null) {
            return Result.error(CodeMsg.SESSION_ERROR);
        }
        try {
            BufferedImage image  = miaoshaService.createVerifyCode(user, goodsId);
            OutputStream out = response.getOutputStream();
            ImageIO.write(image, "JPEG", out);
            out.flush();
            out.close();
            return null;
        }catch(Exception e) {
            e.printStackTrace();
            return Result.error(CodeMsg.MIAOSHA_FAIL);
        }
    }
}

        /*
        //判断库存
        GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
        int stock = goods.getStockCount();
        if(stock <=0){
            return Result.error(CodeMsg.MIAOSHA_OVER);
        }

        //判断是否重复秒杀到
        MiaoshaOrder order = orderService.getMiaoshaOrderByUserIdGoodsId(user.getId(),goodsId);
        if(order != null){
            return Result.error(CodeMsg.REPEATE_MIAOSHA);
        }

        //减库存 下订单 写入秒杀订单
        OrderInfo orderInfo = miaoshaService.miaosha(user, goods);
        return Result.success(orderInfo);
        */