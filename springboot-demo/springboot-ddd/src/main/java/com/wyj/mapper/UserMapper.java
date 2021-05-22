package com.wyj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wyj.entity.po.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserMapper extends BaseMapper<User> {

    /**
     * 新增数据
     *
     * @param user
     */
     void insertUser(User user);

    /**
     * 修改数据
     *
     * @param user
     */
     void updateUser(User user);

    /**
     * 删除数据
     *
     * @param id
     */
     void deleteUser(String id);

    /**
     * 查找单条数据
     *
     * @param id
     * @return
     */
     User selectUser(String id);

    /**
     * 查找多条数据
     *
     * @param user
     * @return
     */
     List<User> selectUserList(User user);

}
