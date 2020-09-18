package com.zxp;

import com.zxp.service.EnableHelloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 采用开关方式实现对于Starter的自动配置实现开关
 * 也可以使用resources/META-INF/spring.factories的spi机制实现自动的配置
 * 启动
 */
@EnableHelloConfig
@SpringBootApplication
public class HelloSpringBootStarter {

	public static void main(String[] args) {
		SpringApplication.run(HelloSpringBootStarter.class, args);
	}
}
