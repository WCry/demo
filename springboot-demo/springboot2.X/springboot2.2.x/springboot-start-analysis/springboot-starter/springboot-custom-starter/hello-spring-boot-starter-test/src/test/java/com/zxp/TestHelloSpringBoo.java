package com.zxp;


import com.zxp.controller.HelloController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest(classes = HelloSpringBootStarter.class)
public class TestHelloSpringBoo{
    @Autowired
	HelloController helloController;
	@Test
	public void contextLoads() {
		System.out.println(helloController.hello("name"));
	}

}
