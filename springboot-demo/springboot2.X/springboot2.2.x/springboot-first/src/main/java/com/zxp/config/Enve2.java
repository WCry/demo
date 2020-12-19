package com.zxp.config;

import org.springframework.context.EnvironmentAware;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @author zhangxuepei
 * @since 3.0
 */
@Component
@Order(0)
public class Enve2 {
    Enve2(){
        System.out.println("初始化2");
    }


}
