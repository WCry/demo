package com.zxp.demo.repository;

import com.zxp.demo.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

//采用继承 注解 注入进来 依赖类
public interface OrderDAO extends JpaRepository<Order, Long> {
}
