package com.zxp.controller;

import com.zxp.scope.SingleAService;
import com.zxp.scope.SingleCService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachePut;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
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
        return "hello , " +port+":"+ name+ "21212";
    }
    @GetMapping("/getName")
    public String hello() {
        log.debug("classC::"+ singleCService.getName());
        log.debug("classAService::"+ singleAService.getName());
        return "hello";
    }
    @GetMapping("/getOptional")
    public Map helloOptional(String name) {
        Map<String,Object> dasd=new HashMap<>();
        log.debug("classC::"+ singleCService.getName());
        log.debug("classAService::"+ singleAService.getName());
        dasd.put("data",Optional.ofNullable(name));
        return dasd;
    }

}
