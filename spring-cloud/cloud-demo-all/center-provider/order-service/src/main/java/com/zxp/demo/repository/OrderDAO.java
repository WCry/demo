package com.zxp.demo.repository;

import com.zxp.demo.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderDAO extends JpaRepository<Order, Long> {

}
