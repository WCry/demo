package com.zxp.controller;

import com.zxp.entity.po.User;
import com.zxp.entity.vo.ApiResponse;
import com.zxp.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpRequest;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class RedisController {

    private final StringRedisTemplate redisTemplate;
    @Autowired HttpServletRequest httpRequest ;

    @Autowired
    public RedisController(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @RequestMapping(value = "/test")
    public ApiResponse test() {
        System.out.println(httpRequest.getMethod());
        User user = new User();
        user.setId(1);
        user.setUsername("张三");
        user.setPassword("abc");
        redisTemplate.opsForValue().set("json", JsonUtils.objectToJson(user));
        User jsonUser = JsonUtils.jsonToPojo(redisTemplate.opsForValue().get("json"), User.class);
        System.out.println(jsonUser.toString());
        redisTemplate.opsForValue().set("aaa", "哈哈哈");
        return ApiResponse.ok(redisTemplate.opsForValue().get("aaa"));
    }
}
