package com.zxp.sso.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

/**
 * user:zxp
 * Day:2020,07,27
 **/
@RestController
public class HelloController {
    //所有的Get请求都不需要进行认证
    @GetMapping("/image/code")
    public String imageCode(HttpServletRequest request, HttpServletResponse response) throws Exception{
       return "12345";
    }
    @RequestMapping("/hello")
    public String hello(Principal principal) {
        System.out.println(principal.toString());
        return "hello " + principal.getName();
    }

    @GetMapping("/")
    public String redirect() {
        return "redirect";
    }
}
