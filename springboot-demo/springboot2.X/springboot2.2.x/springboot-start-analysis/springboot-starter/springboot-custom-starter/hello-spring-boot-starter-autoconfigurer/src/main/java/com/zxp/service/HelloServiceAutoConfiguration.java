package com.zxp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnWebApplication  //当是Web应用的时候启动
@EnableConfigurationProperties(HelloProperties.class)
public class HelloServiceAutoConfiguration {

    final HelloProperties helloProperties;

    @Autowired
    public HelloServiceAutoConfiguration(HelloProperties helloProperties) {this.helloProperties = helloProperties;}

    @Bean
    public HelloService helloService() {
        HelloService service = new HelloService();
        service.setHelloProperties(helloProperties);
        return service;
    }


}
