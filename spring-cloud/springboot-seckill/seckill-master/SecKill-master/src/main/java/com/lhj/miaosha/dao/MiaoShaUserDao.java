package com.lhj.miaosha.dao;

import com.lhj.miaosha.domain.MiaoshaUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface MiaoShaUserDao {
    @Select("select id as 'id',nickname as 'nickname',password as 'password',salt as 'salt',head as 'head'" +
            ",register_date as 'registerDate',last_login_date as 'lastLoginDate',login_count as 'loginCount'" +
            "from miaosha_user where id = #{id}")
     MiaoshaUser getById(@Param("id") long id);

    @Update("update miaosha_user set password = #{password} where id = #{id}")
    void update(MiaoshaUser toBeUpdate);
}
