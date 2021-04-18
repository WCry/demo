package com.example.jpa.service;

import com.example.jpa.projo.User;
import com.example.jpa.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl {
    private final UserRepository testUserDao;

    public UserServiceImpl(UserRepository testUserDao) {
        this.testUserDao = testUserDao;
    }
    @Transactional
    public void insertValue() {
        User user = new User();
        user.setName("zhang san");
        user.setBirth("1990");
        testUserDao.save(user);
    }
}
