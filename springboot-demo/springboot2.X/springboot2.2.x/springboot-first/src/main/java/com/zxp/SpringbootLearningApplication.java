package com.zxp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 *
 */
@SpringBootApplication
@EnableAsync
public class SpringbootLearningApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootLearningApplication.class, args);
    }


    @Bean(initMethod = "initMethod", destroyMethod = "destroyMethod")
    public CustomerBean testIniBean() {
        return new CustomerBean();
    }
}
