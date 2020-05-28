package com.crhms.security.authorizationserver.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class UserController {

    @RequestMapping("/user/me")
    public Principal user(Principal principal) {
        System.out.println(principal);
        return principal;
    }

    @RequestMapping("/hello")
    public String hello() {
        System.out.println("This is a message from authentication server");
        return "This is a message from authentication server";
    }
}
