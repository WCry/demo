package com.example.srpingannotation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
/**
 * @author zhangxuepei
 * @since 3.0
 */

@SpringBootTest
public class HelloBoyTest {

    @Autowired
    HelloBoy helloBoy;

    @Test
    public void HelloBoyTest() {
        helloBoy.sayHello();
    }

}
