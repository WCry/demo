package com.zxp.api.controller.repository;

import com.zxp.api.controller.po.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
