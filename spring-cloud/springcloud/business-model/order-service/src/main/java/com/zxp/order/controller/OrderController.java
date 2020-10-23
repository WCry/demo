package com.zxp.order.controller;

import com.zxp.order.service.OrderService;
import com.zxp.order.OrderAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class OrderController implements OrderAPI {

    @Autowired
    private OrderService orderService;

    @GetMapping("/create")
    public Boolean create(String userId, String commodityCode, Integer count) {

        orderService.create(userId, commodityCode, count);
        return true;
    }

}
