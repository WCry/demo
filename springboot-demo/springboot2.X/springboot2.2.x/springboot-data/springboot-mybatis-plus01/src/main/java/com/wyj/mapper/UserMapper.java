package com.wyj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wyj.entity.po.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<User> {
    @Select({"SELECT 1 FROM user where id=1 limit #{waterLine,jdbcType=INTEGER},1"})
    Boolean dddd(@Param("waterLine") Integer waterLine);

    @Select({"SELECT username FROM user where id= #{id,jdbcType=INTEGER}"})
    User queryWaterLine(@Param("id") Integer id);
}
