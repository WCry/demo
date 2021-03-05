package com.zxp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller

public class AspectController {

    @GetMapping(value = "/aspect-test")
    @ResponseBody
    public String aspect(@Validated String das) {
        System.out.println("执行aspect方法");
        return "aspect方法返回数据";
    }
}
