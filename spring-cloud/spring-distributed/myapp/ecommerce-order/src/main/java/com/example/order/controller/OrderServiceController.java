package com.example.order.controller;

import com.example.order.dto.OrderDto;
import com.example.order.service.OrderServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhangxuepei
 */
@RestController
public class OrderServiceController {
    final OrderServer orderServer;

    @Autowired
    public OrderServiceController(OrderServer orderServer) {
        this.orderServer = orderServer;
    }

    @PostMapping(value = "/finish")
    public Boolean completeOrder(@RequestBody OrderDto orderDto) {
        System.out.printf("dasd");
        return orderServer.finishOrder(orderDto);
    }
    @GetMapping(value="/finish1")
    public Boolean get() {
        System.out.printf("dasd");
        return true;
    }

}
