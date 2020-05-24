package com.zxp.service.impl;

import com.zxp.entity.po.User;
import com.zxp.mapper.UserMapper;
import com.zxp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wyj
 * @create 2019-03-17 22:28
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void insertUser(User user) {
        userMapper.insertUser(user);
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    @Override
    public void deleteUser(String  id) {
        userMapper.deleteUser(id);
    }

    @Override
    public User selectUser(String id) {
        return userMapper.selectUser(id);
    }

    @Override
    public List<User> selectUserList(User user) {
        return userMapper.selectUserList(user);
    }

}
