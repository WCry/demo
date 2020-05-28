package com.lhj.miaosha.controller;

import com.lhj.miaosha.domain.User;
import com.lhj.miaosha.rabbitmq.MQReceiver;
import com.lhj.miaosha.rabbitmq.MQSender;
import com.lhj.miaosha.redis.RedisService;
import com.lhj.miaosha.redis.UserKey;
import com.lhj.miaosha.result.CodeMsg;
import com.lhj.miaosha.result.Result;
import com.lhj.miaosha.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/demo")
public class sampleController {
    @Autowired
    UserService userService;
    @Autowired
    RedisService redisService;

//    @Autowired
//    MQSender sender;
//
//    @RequestMapping("/mq/headers")
//    @ResponseBody
//    public Result<String> headers() {
//        sender.sendHeaders("hello,lianghj");
//        return Result.success("hello,lianghj");
//        // return new Result(0, "success", "hello,imooc");
//    }
//
//    @RequestMapping("/mq/fanout")
//    @ResponseBody
//    public Result<String> fanout() {
//        sender.sendFanout("hello,lianghj");
//        return Result.success("hello,lianghj");
//        // return new Result(0, "success", "hello,imooc");
//    }
//
//    @RequestMapping("/mq/topic")
//    @ResponseBody
//    public Result<String> mq() {
//        sender.sendTopic("hello,lianghj");
//        return Result.success("hello,lianghj");
//        // return new Result(0, "success", "hello,imooc");
//    }
//
//    @RequestMapping("/mq")
//    @ResponseBody
//    public Result<String> topic () {
//        sender.send("hello,lianghj");
//        return Result.success("hello,lianghj");
//        // return new Result(0, "success", "hello,imooc");
//    }

    @RequestMapping("/thymeleaf")
    public String thymeleaf(Model model){
        model.addAttribute("name","lianghj");
        return "hello";

    }
    @RequestMapping("/hello")
    @ResponseBody
    public Result<String> hello() {
        return Result.success("hello,imooc");
    }
    @RequestMapping("/helloError")
    @ResponseBody
    public Result<String> helloError() {
        return Result.error(CodeMsg.SERVER_ERROR);
        //return new Result(500102, "XXX");
    }
    @RequestMapping("/db/get")
    public String  dbGet(Model model){
        User user = userService.getById(1);
        model.addAttribute("id",user.getId());
        model.addAttribute("name",user.getName());
        model.addAttribute("user",user);
        return "hello";

    }
    @RequestMapping("/db/tx")
    public Result<Boolean> dbTx(Model model){
        userService.tx();
        return Result.success(true);

    }

    @RequestMapping("/redis/get")
    @ResponseBody
    public Result<User> redisGet() {
        User user  = redisService.get(UserKey.getById, ""+1, User.class);
        return Result.success(user);
    }

    @RequestMapping("/redis/set")
    @ResponseBody
    public Result<Boolean> redisSet() {
        User user  = new User();
        user.setId(1);
        user.setName("1111");
        redisService.set(UserKey.getById, ""+1, user);//UserKey:id1
        return Result.success(true);
    }
}
