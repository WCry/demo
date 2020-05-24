package com.zxp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.util.ErrorHandler;

import java.util.concurrent.Executor;

/**
 * @SpringBootApplication 来标注一个主程序，说明是一个Spring Boot 应用
 */
@SpringBootApplication
@EnableAsync
public class SpringbootFirstApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootFirstApplication.class, args);
	}

	/**
	 *  配置一个事件广播者  和系统广播者
	 *  是分开的  系统注册两个事件广播者
	 *  todo:该过程关于线程池和错误处理调用为进行设置，demo根据需要实现
	 * @return
	 */

	public ApplicationEventMulticaster  applicationEventMulticaster(){
		SimpleApplicationEventMulticaster applicationEventMulticaster=new SimpleApplicationEventMulticaster();
		Executor executor=null;
		applicationEventMulticaster.setTaskExecutor(executor);
		ErrorHandler errorHandler=null;
		applicationEventMulticaster.setErrorHandler(errorHandler);
		return applicationEventMulticaster;
	}
}
