package com.zxp.dao;

import com.zxp.entity.po.UserStudent;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * 他是具有分页和排序的功能 同时他继承啦 CrudRepository
 */
public interface UserPageRepository extends PagingAndSortingRepository<UserStudent,Long> {
}
