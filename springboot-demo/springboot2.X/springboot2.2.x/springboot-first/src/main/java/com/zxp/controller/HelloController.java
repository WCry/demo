package com.zxp.controller;

import com.zxp.TestInstance;
import com.zxp.TestServer;
import com.zxp.service.ClassAService;
import com.zxp.service.ClassC;
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
    private ClassAService classAService;
    @Autowired
    private ClassC classC;
    @GetMapping("/{name}")
    @CachePut("dsad")
    public String hello(@PathVariable(name = "name")  String name) {
        System.out.println(TestInstance.getInstance());
//        classAService.printClass();
//        classC.setName(name);
//        System.out.println("classC：："+classC.getName());
//        classAService.setName(name);
//        System.out.println("classAService::"+classAService.getName());
        return "hello , " +port+":"+ name;
    }
    @GetMapping("/getName")
    public String hello() {
        System.out.println("classC::"+classC.getName());
        System.out.println("classAService::"+classAService.getName());
        return "hello";
    }

}
