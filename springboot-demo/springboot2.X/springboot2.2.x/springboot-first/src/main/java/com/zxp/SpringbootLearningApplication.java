package com.zxp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.LinkedList;

/**
 * @SpringBootApplication 来标注一个主程序，说明是一个Spring Boot 应用
 */
@SpringBootApplication
@EnableAsync
public class SpringbootLearningApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootLearningApplication.class, args);
	}
	@Bean(initMethod = "initMethod", destroyMethod = "destroyMethod")
	public CustomerBean testIniBean(){
		return new CustomerBean();
	}
}
