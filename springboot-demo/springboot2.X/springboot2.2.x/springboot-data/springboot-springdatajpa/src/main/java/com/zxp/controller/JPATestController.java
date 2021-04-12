package com.zxp.controller;

import com.zxp.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JPATestController {


   private final UserService userService;

    public JPATestController(UserService userService) {
        this.userService = userService;

    }
    @GetMapping("/test/get")
    public String getUser() throws InterruptedException {
        userService.findUser();
        return "sssss";
    }
    @GetMapping("/test/get/sleep")
    public String getUserSleep() throws InterruptedException {
        userService.findUserSleep();
        return "sssss";
    }

    @GetMapping("/test/update")
    public String TestUpdateUser() throws InterruptedException {
        userService.updateUser();
        return "sssss";
    }
    @GetMapping("/test/update/sleep")
    public String TestUpdateUserSleep() throws InterruptedException {
        userService.updateUserSleep();
        return "sssss";
    }

}
