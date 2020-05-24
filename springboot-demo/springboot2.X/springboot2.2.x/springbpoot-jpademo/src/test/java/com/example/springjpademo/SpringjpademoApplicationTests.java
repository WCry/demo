package com.example.springjpademo;

import com.example.springjpademo.projo.User;
import com.example.springjpademo.repositorie.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class SpringjpademoApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private UserRepository testUserDao;

    @Test
    public void insert() {
        User user= new User();
        user.setName("zhang san");
        user.setBirth("1990");
        testUserDao.save(user);
    }

    @Test
    public void update() {
        User user = new User();
        user.setId(1L);
        user.setBirth("1990");
        user.setName("李四");
        testUserDao.save(user);
    }

    @Test
    public void select() {
        Optional<User> user = testUserDao.findById(1L);
        System.out.println(user.get().getName());
        if(user.isPresent()){
            System.out.println(user.get().getName());
        }else{
            System.out.println("不存在");
        }
    }

    @Test
    public void delete() {
        testUserDao.deleteById(1L);
    }
}
