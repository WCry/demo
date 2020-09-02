package com.zxp.cart.service;

import com.zxp.cart.feign.OrderFeignClient;
import com.zxp.cart.feign.StorageFeignClient;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BusinessService {

    private final StorageFeignClient storageFeignClient;
    private final OrderFeignClient orderFeignClient;

    @Autowired
    public BusinessService(StorageFeignClient storageFeignClient, OrderFeignClient orderFeignClient) {
        this.storageFeignClient = storageFeignClient;
        this.orderFeignClient = orderFeignClient;
    }

    /**
     * 减库存，下订单
     *
     * @param userId
     * @param commodityCode
     * @param orderCount
     */
    @GlobalTransactional
    public void purchase(String userId, String commodityCode, int orderCount) {
        storageFeignClient.deduct(commodityCode, orderCount);
        orderFeignClient.create(userId, commodityCode, orderCount);
    }
}
