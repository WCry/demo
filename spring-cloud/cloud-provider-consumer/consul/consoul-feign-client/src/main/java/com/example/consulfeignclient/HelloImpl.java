package com.example.consulfeignclient;

import com.example.consulfeignapi.Gender;
import com.example.consulfeignapi.HelloService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangxuepei
 * @since 3.0
 */
@RestController
public class HelloImpl implements HelloService {
    @Override
    public String hello(String name) {
        return "hello " + name;
    }

    @Override
    public Gender getGender(String name) {
        return Gender.MAN;
    }
}
