package com.zxp.dao;

import com.zxp.entity.po.UserCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository
 * 用户信用卡
 */
@Repository
public interface UserCardRepository extends JpaRepository<UserCard, Integer> {}
