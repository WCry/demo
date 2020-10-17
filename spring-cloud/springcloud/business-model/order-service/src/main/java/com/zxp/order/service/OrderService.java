package com.zxp.order.service;

import com.zxp.account.pojo.ChangeParams;
import com.zxp.order.entity.Order;
import com.zxp.order.feign.AccountFeignClient;
import com.zxp.order.repository.OrderDAO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;


@Service
public class OrderService {

    private final AccountFeignClient accountFeignClient;

    private final OrderDAO orderDAO;

    public OrderService(AccountFeignClient accountFeignClient, OrderDAO orderDAO) {
        this.accountFeignClient = accountFeignClient;
        this.orderDAO = orderDAO;
    }

    @Transactional
    public void create(String userId, String commodityCode, Integer count) {

        BigDecimal orderMoney = new BigDecimal(count).multiply(new BigDecimal(5));

        Order order = new Order();
        order.setUserId(userId);
        order.setCommodityCode(commodityCode);
        order.setCount(count);
        order.setMoney(orderMoney);

        orderDAO.save(order);
        ChangeParams changeParams=new ChangeParams(userId,orderMoney);
        accountFeignClient.debitAccount(changeParams);

    }

}
