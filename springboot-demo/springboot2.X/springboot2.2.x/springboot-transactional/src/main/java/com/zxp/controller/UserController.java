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

    @Autowired
    private UserService userService;
    @Autowired
    private UserWithCardService withCardService;

    @RequestMapping(value = "findAll")
    public List<User> findAll() {
        List<User> userList = userService.findAll();
        return userList;
    }

    @PostMapping(value = "addUserCard")
    public Boolean addUser(@RequestBody UserWithCard userWithCard) {
       User user=new User();
        BeanUtils.copyProperties(userWithCard,user);
        withCardService.addUser(user, userWithCard.getCount());
        return true;
    }
}
