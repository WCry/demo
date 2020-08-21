package com.zxp;

/**
 * @author zhangxuepei
 * @since 3.0
 */
public class CustomerBean {
    public void initMethod(){
        System.out.println("@Bean 初始化设置，资源初始化");
    }
    public void destroyMethod(){
        System.out.println("@Bean资源释放，资源被释放了");
    }
}
