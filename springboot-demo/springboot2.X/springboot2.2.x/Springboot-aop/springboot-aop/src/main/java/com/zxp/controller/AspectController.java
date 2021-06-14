package com.zxp.controller;

import com.zxp.springaop.InterceptorAnnotation;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AspectController {

    @InterceptorAnnotation
    @GetMapping(value = "/aspect-test")
    @ResponseBody
    public String aspect(@Validated String das) {
        System.out.println("执行aspect方法");
        return "aspect方法返回数据";
    }
    @ExceptionHandler
    public void dddd(Exception exception){
        System.out.println("dddd");
    }
}
