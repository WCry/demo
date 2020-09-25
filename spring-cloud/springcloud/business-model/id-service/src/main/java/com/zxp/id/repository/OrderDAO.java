package com.zxp.id.repository;

import com.zxp.id.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderDAO extends JpaRepository<Order, Long> {

}
