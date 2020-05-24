package com.lhj.miaosha.rabbitmq;

import com.lhj.miaosha.domain.MiaoshaOrder;
import com.lhj.miaosha.domain.MiaoshaUser;
import com.lhj.miaosha.redis.RedisService;
import com.lhj.miaosha.service.GoodsService;
import com.lhj.miaosha.service.MiaoshaService;
import com.lhj.miaosha.service.MiaoshaUserService;
import com.lhj.miaosha.service.OrderService;
import com.lhj.miaosha.vo.GoodsVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ：LHJ
 * @date ：2019/7/30 16:44
 * @description：${description}
 */
@Service
public class MQReceiver {
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

    private static Logger log = LoggerFactory.getLogger(MQReceiver.class);

    @RabbitListener(queues=MQConfig.MIAOSHA_QUEUE)
    public void receive(String message){
        log.info("receive message :" + message);
        MiaoshaMessage message1 = RedisService.stringToBean(message,MiaoshaMessage.class);
        MiaoshaUser user = message1.getUser();
        long goodsId = message1.getGoodsId();

        //判断库存
        GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
        int stock = goods.getStockCount();
        if(stock <=0){
            return ;
        }

        //判断是否重复秒杀到
        MiaoshaOrder order = orderService.getMiaoshaOrderByUserIdGoodsId(user.getId(),goodsId);
        if(order != null){
            return ;
        }
        ////减库存 下订单 写入秒杀订单
        miaoshaService.miaosha(user, goods);
    }

//    @RabbitListener(queues=MQConfig.QUEUE)
//    public void receive(String message){
//        log.info("receive message :" + message);
//
//    }
//    @RabbitListener(queues = MQConfig.TOPIC_QUEUE1)
//    public void receiveTopic1(String message){
//        log.info("receive topic queue1 message :" + message);
//
//    }
//
//    @RabbitListener(queues = MQConfig.TOPIC_QUEUE2)
//    public void receiveTopic2(String message){
//        log.info("receive topic queue2 message :" + message);
//
//    }
//
//    @RabbitListener(queues = MQConfig.HEADERS_QUEUE)
//    public void receiveHeaders(byte []message){
//        log.info("receive headers queue message :" + new String(message));
//
//    }
}
