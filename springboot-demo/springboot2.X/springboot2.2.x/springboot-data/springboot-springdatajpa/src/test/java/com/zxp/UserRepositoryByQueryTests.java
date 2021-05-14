package com.zxp;


import com.zxp.dao.UserRepositoryByQuery;
import com.zxp.entity.po.User;
import com.zxp.entity.po.UserIdAndName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryByQueryTests {

    @Autowired
    private UserRepositoryByQuery userRepository;

    @Test
    public void findUserTest() {
        Long id = 1L;
        User user = userRepository.findUser(id);
        user.setName("dsad");
        userRepository.save(user);
        UserIdAndName dsa=userRepository.findUserByNativeQuery(1);
        System.out.println(dsa.getClass());
    }

    @Test
    public void findUserByParamTest() {
        Long id = 1L;
        User user = userRepository.findUserByParam(id);
        System.out.println("user:" + user);
    }

    @Test
    public void findUserByIdAndNameTest() {
        Long id = 1L;
        String name = "ljk";
        User user = userRepository.findUserByIdAndName(id, name);
        System.out.println("user:" + user);
    }

    @Test
    public void findUserListTest() {
        List<User> userList = userRepository.findUserList();
        System.out.println("userList:" + userList);
    }

    @Test
    public void findUserListByLikeNameTest() {
        String name = "j";
        List<User> userListByLikeName = userRepository.findUserListByLikeName(name);
        System.out.println("userListByLikeName:" + userListByLikeName);
    }

    @Test
    public void findUserListByLikeConcatNameTest() {
        String name = "j";
        List<User> userListByLikeName = userRepository.findUserListByLikeConcatName(name);
        System.out.println("userListByLikeName:" + userListByLikeName);
    }

    @Test
    public void findUserByNativeQueryTest() {
        int id = 1;
        UserIdAndName user = userRepository.findUserByNativeQuery(id);
        System.out.println("user:" + user);
    }

    @Test
    public void findByLastnameTest() {
        String str = "wyj";
        List<User> userList = userRepository.findBySpEL(str);
        System.out.println("userList:" + userList);
    }

}
