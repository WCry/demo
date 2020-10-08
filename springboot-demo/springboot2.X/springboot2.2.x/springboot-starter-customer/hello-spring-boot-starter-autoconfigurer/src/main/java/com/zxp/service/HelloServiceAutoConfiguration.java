package com.zxp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//表明是配置类
@Configuration
//条件编译 可以用于类上 也可以用于方法上
@ConditionalOnWebApplication
//当是Web应用的时候启动  开启属性到实体对象的映射
@EnableConfigurationProperties(HelloProperties.class)
public class HelloServiceAutoConfiguration {

    final HelloProperties helloProperties;

    @Autowired
    public HelloServiceAutoConfiguration(HelloProperties helloProperties) {this.helloProperties = helloProperties;}

    /**
     * 配置文件中注入Bean
     */
    @Bean
    public HelloService helloService() {
        HelloService service = new HelloService();
        service.setHelloProperties(helloProperties);
        return service;
    }


}
