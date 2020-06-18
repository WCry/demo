package com.zxp.springbootfirstapplication;

import com.zxp.service.ClassAService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootFirstApplicationTests {

	@Test
	public void contextLoads() {
	}
	@Autowired
	private ClassAService classA;

	@Test
	public void simpleTest() {
		for (int i = 0; i < 3; i++) {
			classA.printClass();
		}
	}
}
