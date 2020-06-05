package com.example.consulfeignconsumer;

import com.ecwid.consul.v1.ConsulClient;
import com.example.consulfeignapi.Gender;
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
    private final HelloServiceClient helloServiceClient;
    @Autowired
    private ConsulClient consulClient;
    @Autowired
    private LoadBalancerClient loadBalancer;
    public HelloTestController(HelloServiceClient helloServiceClient) {
        this.helloServiceClient = helloServiceClient;
    }

    @GetMapping("/test")
    public String test(String name) {
        return helloServiceClient.hello(name);
    }

    @GetMapping("/gender")
    public Gender getGender(String name) {
        Gender gender=helloServiceClient.getGender(name);
        return gender;
    }
    @GetMapping("/language")
    public Language getLanage(String name) {
        Language language=helloServiceClient.countyLanguage(name);
        return language;
    }

}
