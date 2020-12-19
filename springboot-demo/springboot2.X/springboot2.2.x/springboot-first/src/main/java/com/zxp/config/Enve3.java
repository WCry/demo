package com.zxp.config;

import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @author zhangxuepei
 * @since 3.0
 */
@Configuration
public class Enve3 implements EnvironmentAware {
    Enve3(){
        System.out.println("初始化");
    }
    @Override
    public void setEnvironment(Environment environment) {
        System.out.println("dsad");
    }
}
