package com.wyj;

import com.wyj.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootMybatisPlus01ApplicationTests {

    @Autowired
    UserMapper userMapper;
    @Test
    public void contextLoads() {
        System.out.println(userMapper.dddd(5));
        System.out.println(userMapper.dddd(0));
    }

}
