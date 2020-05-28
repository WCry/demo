package com.lhj.miaosha.controller;

import com.lhj.miaosha.domain.MiaoshaUser;
import com.lhj.miaosha.redis.RedisService;
import com.lhj.miaosha.result.Result;
import com.lhj.miaosha.service.MiaoshaUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ：LHJ
 * @date ：2019/7/28 8:51
 * @description：${description}
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    MiaoshaUserService userService;

    @Autowired
    RedisService redisService;

    @RequestMapping("/info")
    @ResponseBody
    public Result<MiaoshaUser> info(Model model, MiaoshaUser user) {
        return Result.success(user);
    }
}
