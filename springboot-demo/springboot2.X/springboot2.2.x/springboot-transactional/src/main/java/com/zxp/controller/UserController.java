package com.zxp.controller;

import com.zxp.dto.UserWithCard;
import com.zxp.entity.po.User;
import com.zxp.service.UserService;
import com.zxp.service.UserWithCardService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;
    private final UserWithCardService userWithCardService;
    @Autowired
    public UserController(UserService userService, UserWithCardService userWithCardService) {
        this.userService = userService;
        this.userWithCardService = userWithCardService;
    }

    @RequestMapping(value = "findAll")
    public List<User> findAll() {
        List<User> userList = userService.findAll();
        return userList;
    }

    @PostMapping(value = "addUserCard")
    public Boolean addUser(@RequestBody UserWithCard userWithCard) {
        User user = new User();
        BeanUtils.copyProperties(userWithCard, user);
        userWithCardService.addUser(user, userWithCard.getCount());
        return true;
    }
}
