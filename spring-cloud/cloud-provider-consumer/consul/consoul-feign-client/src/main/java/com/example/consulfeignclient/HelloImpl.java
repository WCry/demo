package com.example.consulfeignclient;

import com.example.consulfeignapi.Gender;
import com.example.consulfeignapi.HelloService;
import org.springframework.stereotype.Service;

/**
 * @author zhangxuepei
 * @since 3.0
 */
@Service
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
