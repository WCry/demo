package com.zxp.id.controller;

import com.zxp.user.api.order.OrderAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class OrderController implements OrderAPI {

    @Autowired
    private com.zxp.id.service.IDService IDService;

    @GetMapping("/create")
    public Boolean create(String userId, String commodityCode, Integer count) {

        IDService.create(userId, commodityCode, count);
        return true;
    }

}
