package com.zxp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zxp.entry.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    //注解方式获取配置文件中的属性
    @Value("${server.port}")
    private String port;

    @PostMapping(value = "/user", consumes = "application/json;charset=UTF-8")
    public User postUser(@RequestBody User user) {
        return user;
    }


    @GetMapping("/{name}")
    public User hello(@PathVariable(name = "name") String name) {
        User user = new User();
        user.setName("hello , " + port + ":" + name);
        return user;
    }

}
