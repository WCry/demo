package com.zxp.order.repository;

import com.zxp.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderDAO extends JpaRepository<Order, Long> {

}
