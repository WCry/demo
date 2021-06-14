package com.zxp.controller;

/**
 * @author zhangxuepei
 * @since 3.0
 */

import com.zxp.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/ddd", method = RequestMethod.GET)
    public String saveUser() {
        return "dddd";
    }
    @ExceptionHandler
    public void dddd(Exception exception){
        System.out.println("dddd");
    }
}
