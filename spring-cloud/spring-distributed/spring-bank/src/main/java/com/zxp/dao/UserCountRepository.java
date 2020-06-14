package com.zxp.dao;

import com.zxp.entity.po.UserCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository
 * 注解作为仓库
 */
@Repository
public interface UserCountRepository extends JpaRepository<UserCount, Integer> {
      UserCount findAllByUserID(String userID);
}
