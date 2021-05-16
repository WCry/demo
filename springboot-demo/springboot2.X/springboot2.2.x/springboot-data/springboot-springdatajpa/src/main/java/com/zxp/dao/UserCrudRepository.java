package com.zxp.dao;

import com.zxp.entity.po.UserStudent;
import org.springframework.data.repository.CrudRepository;

/**
 * 具有增删改查的一些基本功能
 */
public interface UserCrudRepository extends CrudRepository<UserStudent, Long> {
}
