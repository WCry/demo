package com.zxp.seckill.controller;


import com.zxp.seckill.bean.User;
import com.zxp.seckill.redis.RedisService;
import com.zxp.seckill.result.Result;
import com.zxp.seckill.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/user")
public class UserController {

    final UserService userService;

    final RedisService redisService;

    public UserController(UserService userService, RedisService redisService) {
        this.userService = userService;
        this.redisService = redisService;
    }

    @RequestMapping("/info")
    @ResponseBody
    public Result<User> info(Model model, User user) {
        return Result.success(user);
    }
}