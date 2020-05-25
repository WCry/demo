package com.zxp.service;

import org.springframework.context.annotation.Import;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zhangxuepei
 * @since 3.0
 * 可以通过注解方式实现，实现开关启动
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(HelloServiceAutoConfiguration.class)
public @interface EnableHelloConfig {}
