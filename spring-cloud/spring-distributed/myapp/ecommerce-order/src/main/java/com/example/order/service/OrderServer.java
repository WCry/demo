package com.example.order.service;

import com.example.order.dto.OrderDto;

/**
 * user:zxp
 * Day:2020,06,14
 **/
public interface OrderServer {
    Boolean finishOrder(OrderDto orderDto);
    Boolean newOrder(OrderDto orderDto);
}
