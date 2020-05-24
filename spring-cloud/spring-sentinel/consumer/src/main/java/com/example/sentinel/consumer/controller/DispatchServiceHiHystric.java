package com.example.sentinel.consumer.controller;

import com.example.api.User;
import com.example.sentinel.consumer.DispatchServiceHi;
import org.springframework.stereotype.Component;

@Component
public class DispatchServiceHiHystric implements DispatchServiceHi {
    @Override
    public String sayHiOne(User json) {
        return "sorry " + json;
    }
}
