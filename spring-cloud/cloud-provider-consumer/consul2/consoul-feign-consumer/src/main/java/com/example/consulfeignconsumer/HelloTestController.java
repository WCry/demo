package com.example.consulfeignconsumer;

import com.ecwid.consul.v1.ConsulClient;
import com.example.consulfeignapi.Gender;
import com.example.consulfeignapi.HelloService;
import com.example.consulfeignapi.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangxuepei
 */
@RestController
public class HelloTestController {
    @Autowired
    ConsumerServiceClient consumerServiceClient;
    public HelloTestController() {

    }

    @PostMapping("/test")
    public Integer test() throws InterruptedException {
       Thread.sleep(100);
       return 100;
    }
    @GetMapping("/test2")
    public String test2(String name) {
        consumerServiceClient.test();
        return name;
    }
}
