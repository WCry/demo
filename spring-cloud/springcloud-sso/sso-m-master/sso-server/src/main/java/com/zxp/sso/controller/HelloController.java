package com.zxp.sso.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * user:zxp
 * Day:2020,07,27
 **/
@RestController
public class HelloController {

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
