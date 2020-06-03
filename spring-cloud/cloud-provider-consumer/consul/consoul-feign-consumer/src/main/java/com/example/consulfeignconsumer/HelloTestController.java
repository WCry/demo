package com.example.consulfeignconsumer;

import com.example.consulfeignapi.Gender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangxuepei
 * @since 3.0
 */
@RestController
public class HelloTestController {
    private final HelloServiceClient helloServiceClient;

    public HelloTestController(HelloServiceClient helloServiceClient) {
        this.helloServiceClient = helloServiceClient;
    }

    @GetMapping("/test")
    public String test(String name) {
        return helloServiceClient.hello(name);
    }

    @GetMapping("/gender")
    public Gender getGender(String name) {
        return helloServiceClient.getGender(name);
    }
}
