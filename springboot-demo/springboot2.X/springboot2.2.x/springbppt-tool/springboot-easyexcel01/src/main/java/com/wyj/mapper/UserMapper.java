package com.wyj.mapper;

import com.wyj.entity.po.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    /**
     * 查询所有用户
     */
    public List<User> findAll();

}
