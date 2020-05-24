package com.lhj.miaosha.dao;

import com.lhj.miaosha.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserDao {

    @Select("select id as 'id',nikname as 'name' from miaosha_user where id = #{id}")
     User getById(@Param("id") int id);

    @Insert("insert into miaosha_user(id, nickname)values(#{id}, #{name})")
     int insert(User user);

}
