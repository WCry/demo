package com.example.order;

import com.example.order.dto.OrderDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangxuepei
 */
@RestController("/order")
public class OrderServiceController {
    @PostMapping("/finish")
    public Boolean completeOrder(OrderDto orderDto) {
        return true;
    }

}
