package com.zxp;


import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AopApplicationTests {
    /**
     * 测试类加载前进行
     */
    @BeforeClass
    public static void beforeTestClass() {
        System.out.println("测试类之前");
    }
    /**
     * 测试方法加载之前运行，每一个测试类之前都会运行
     */
    @Before
    public void beforeTest() {
        System.out.println("测试前");
    }
    /**
     * 测试方法
     */
    @Test
    public void contextLoads() {
        System.out.println("测试初始化");
    }
    @Test
    public void Test2() {
        System.out.println("测试2");
    }
}
