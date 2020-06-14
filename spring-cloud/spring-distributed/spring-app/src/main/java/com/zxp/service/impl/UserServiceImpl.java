package com.zxp.service.impl;

import com.zxp.dao.UserRepository;
import com.zxp.entity.po.User;
import com.zxp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void addUser(User user) {
        if (StringUtils.isEmpty(user.getUserID())) {
            UUID uuid = UUID.randomUUID();
            user.setUserID(uuid.toString());
        }
        userRepository.save(user);
    }
}
