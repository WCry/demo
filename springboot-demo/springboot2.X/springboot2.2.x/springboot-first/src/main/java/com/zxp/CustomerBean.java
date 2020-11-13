package com.zxp;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zhangxuepei
 * @since 3.0
 */
@Slf4j
public class CustomerBean {
    public void initMethod(){
        log.debug("@Bean 初始化设置，资源初始化");
    }
    public void destroyMethod(){
        log.debug("@Bean资源释放，资源被释放了");
    }

}
