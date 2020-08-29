package com.zxp.account.repository;

import com.zxp.account.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderDAO extends JpaRepository<Order, Long> {

}
