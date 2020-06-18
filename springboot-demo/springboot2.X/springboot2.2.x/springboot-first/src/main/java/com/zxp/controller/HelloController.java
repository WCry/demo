package com.zxp.controller;

import com.zxp.service.ClassAService;
import com.zxp.service.ClassC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    public String hello(@PathVariable(name = "name")  String name) {
        classAService.printClass();
        classC.setName(name);
        System.out.println("classC：："+classC.getName());
        classAService.setName(name);
        System.out.println("classAService::"+classAService.getName());
        return "hello , " +port+":"+ name;
    }
    @GetMapping("/gteName")
    public String hello() {
        System.out.println("classC::"+classC.getName());
        System.out.println("classAService::"+classAService.getName());
        return "hello";
    }

}
