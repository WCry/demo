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
        return userService.findUser();
    }

    @GetMapping("/test/update")
    public String TestUpdateUser() {
        return userService.updateUser();
    }
}
