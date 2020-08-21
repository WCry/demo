package com.example.consulfeignconsumer;

import com.ecwid.consul.v1.ConsulClient;
import com.example.consulfeignapi.Gender;
import com.example.consulfeignapi.HelloService;
import com.example.consulfeignapi.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangxuepei
 */
@RestController("/test")
public class HelloTestController {
    @Autowired
    HelloService helloService;
    @Autowired
    private ConsulClient consulClient;
    @Autowired
    private LoadBalancerClient loadBalancer;
    public HelloTestController() {

    }

    @GetMapping("/test")
    public String test(String name) {
        helloService.hello("dasd");
       return name;
    }



}
