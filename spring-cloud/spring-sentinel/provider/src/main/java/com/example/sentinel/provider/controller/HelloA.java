package com.example.sentinel.provider.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.example.api.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloA {
    @Value("${server.port}")
    String port;

    @SentinelResource("hi")
    @RequestMapping(value = "/hi", method = RequestMethod.GET)
    public String home(@RequestParam(value = "name", defaultValue = "123") String name) {
        return "hi " + name + " ,i am from port:" + port;
    }

    @RequestMapping(value = "/hi123", method = RequestMethod.GET)
    public String hi(@RequestBody User name) {
        return "hi " + name.getName() + " " + ",i am from port:" + port;
    }
}
