package com.zxp.controller;

import com.zxp.entity.po.User;
import com.zxp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "findAll")
    public List<User> findAll() {
        List<User> userList = userService.findAll();
        return userList;
    }

}
