package com.example.springclouddemo.sericefeign.controller;

import com.example.springclouddemo.sericefeign.SchedualServiceHi;
import common.user;
import org.springframework.stereotype.Component;

@Component
public class SchedualServiceHiHystric implements SchedualServiceHi {
    @Override
    public String sayHiOne(user json) {
        return "sorry "+json;
    }
}
