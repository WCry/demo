package com.zxp;

import com.zxp.dao.UserRepository;
import com.zxp.entity.po.User;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.SQLException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootDruid01ApplicationTests {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void contextLoads() throws SQLException {
        System.out.println(dataSource.getClass());
        System.out.println(dataSource.getConnection());
        User user=new User();
        user.setSex("男");
        user.setAge(12);
        user.setAddress("test");
        user.setName("ddd");
        userRepository.save(user);
        User user2=new User();
        user2.setSex("男");
        user2.setAge(15);
        user2.setAddress("teddddddst");
        user2.setName("dddddd");
        userRepository.save(user2);
    }

}
