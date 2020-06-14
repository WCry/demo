//package com.example.consulfeignclient;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * @author zhangxuepei
// */
//@RestController("/test")
//    private final class helloServiceClient;
//    @Autowired
//    private ConsulClient consulClient;
//    @Autowired
//    private LoadBalancerClient loadBalancer;
//    public HelloTestController(OrderServiceClient helloServiceClient) {
//        this.helloServiceClient = helloServiceClient;
//    }
//
//    @GetMapping("/test")
//    public String test(String name) {
//        return helloServiceClient.hello(name);
//    }
//
//    @GetMapping("/gender")
//    public Gender getGender(String name) {
//        Gender gender=helloServiceClient.getGender(name);
//        return gender;
//    }
//    @GetMapping("/language")
//    public Language getLanage(String name) {
//        Language language=helloServiceClient.countyLanguage(name);
//        return language;
//    }
//
//}
