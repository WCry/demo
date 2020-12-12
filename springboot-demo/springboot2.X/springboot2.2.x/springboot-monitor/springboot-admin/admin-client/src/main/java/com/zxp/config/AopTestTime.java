package com.zxp.config;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author zhangxuepei
 * @since 3.0
 */
@Aspect
@Component
public class AopTestTime {
//    /**
//     * 定义切入点，切入点为com.example.demo.aop.AopController中的所有函数
//     *通过@Pointcut注解声明频繁使用的切点表达式
//     */
//    @Pointcut("execution(public * org.springframework.web.servlet.DispatcherServlet(..)))")
//    public void BrokerAspect(){
//    }
//
//    /**
//     * @description  在连接点执行之前执行的通知
//     */
//    @Before("BrokerAspect()")
//    public void doBeforeGame(){
//        System.out.println("经纪人正在处理球星赛前事务！");
//    }
//
//    /**
//     * @description  在连接点执行之后执行的通知（返回通知和异常通知的异常）
//     */
//    @After("BrokerAspect()")
//    public void doAfterGame(){
//        System.out.println("经纪人为球星表现疯狂鼓掌！");
//    }
//
//    /**
//     * @description  在连接点执行之后执行的通知（返回通知）
//     */
//    @AfterReturning("BrokerAspect()")
//    public void doAfterReturningGame(){
//        System.out.println("返回通知：经纪人为球星表现疯狂鼓掌！");
//    }
//
//    /**
//     * @description  在连接点执行之后执行的通知（异常通知）
//     */
//    @AfterThrowing("BrokerAspect()")
//    public void doAfterThrowingGame(){
//        System.out.println("异常通知：球迷要求退票！");
//    }
}
