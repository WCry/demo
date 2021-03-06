package com.zxp.controller;

import com.zxp.service.UserService;
//参数校验
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    private final UserService userService;

    public TestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/test/get/{userID}")
    public String getUser(@PathVariable("userID") Long userID) {
        return userService.findUser(userID);
    }

    @GetMapping("/test/update/{userID}/age/{age}")
    public String TestUpdateUser(@PathVariable("userID") Long userID, @Validated @PathVariable("age") Integer age) {
        return userService.updateUser(userID,age);
    }
}
