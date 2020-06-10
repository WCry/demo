package com.zxp.dao;

import com.zxp.entity.po.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;

/**
 * Repository
 * 注解作为仓库
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
