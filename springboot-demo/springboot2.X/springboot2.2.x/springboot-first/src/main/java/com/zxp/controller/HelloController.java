package com.zxp.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    //注解方式获取配置文件中的属性
    @Value( "${server.port}" )
    private String port;
    @GetMapping("/{name}")
    public String hello(@PathVariable(name = "name")  String name) {
       return "hello , " +port+":"+ name;
    }

}
