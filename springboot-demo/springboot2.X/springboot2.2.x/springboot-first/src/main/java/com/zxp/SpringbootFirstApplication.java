package com.zxp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

import java.net.InetAddress;

/**
 * @SpringBootApplication 来标注一个主程序，说明是一个Spring Boot 应用
 */
@SpringBootApplication
@EnableAsync
public class SpringbootFirstApplication {

	public static void main(String[] args) {
//		TestServer testServer=new TestServer();
//		testServer.start();
		//Spring 应用启动起来
		SpringApplication.run(SpringbootFirstApplication.class, args);
	}
//	@Bean(initMethod = "",destroyMethod = "")
//	public TestIniBean testIniBean(){
//		return new TestIniBean();
//	}
}
