package com.lhj.miaosha.service;

import com.lhj.miaosha.dao.UserDao;
import com.lhj.miaosha.domain.User;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserService {
    @Autowired
    UserDao userDao;
    public User getById(int id){
        return userDao.getById(id);
    }

    @Transactional
    public boolean tx() {
        User u1 = new User();
        u1.setId(3);
        u1.setName("333");
        userDao.insert(u1);
        User u2 = new User();
        u2.setId(1);
        u2.setName("111");
        userDao.insert(u2);
        return true;

    }
}
