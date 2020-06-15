package com.zxp.controller;

import com.zxp.dto.UserConsume;
import com.zxp.entity.po.UserCount;
import com.zxp.service.UserCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserCountController {

    private final UserCountService userCountService;

    @Autowired
    public UserCountController(UserCountService userCountService) {
        this.userCountService = userCountService;
    }

    @RequestMapping(value = "/consumemoney")
    public Boolean cousumer(@RequestBody UserConsume userConsume) {
        return userCountService.consumer(userConsume);
    }
    @RequestMapping(value = "/addmoney")
    public Boolean addMoney(@RequestBody UserConsume userConsume) {
        return userCountService.consumer(userConsume);
    }

}
