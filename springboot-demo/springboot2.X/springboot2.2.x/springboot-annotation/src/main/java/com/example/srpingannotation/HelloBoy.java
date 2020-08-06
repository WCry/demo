package com.example.srpingannotation;

import com.example.srpingannotation.annotation.Boy;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @author zhangxuepei
 * @since 3.0
 */


@Service
public class HelloBoy {

    @Boy("小明")
    String name = "world";

    public void sayHello() {
        System.out.println("hello, " + name);
    }
}
