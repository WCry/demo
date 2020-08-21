package com.zxp.controller;

import com.zxp.scope.SingleAService;
import com.zxp.scope.SingleCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachePut;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    //注解方式获取配置文件中的属性
    @Value( "${server.port}" )
    private String port;
    @Autowired
    private SingleAService singleAService;

    @Autowired
    private SingleCService singleCService;
    @GetMapping("/dsad/{name}")
    @CachePut("dsad")
    public String hello(@PathVariable(name = "name")  String name) {
        return "hello , " +port+":"+ name;
    }
    @GetMapping("/getName")
    public String hello() {
        System.out.println("classC::"+ singleCService.getName());
        System.out.println("classAService::"+ singleAService.getName());
        return "hello";
    }

}
