package com.zxp.springaop;

/**
 * @author zhangxuepei
 * @since 3.0
 */
import java.lang.annotation.*;

//通过自定义注解的方式
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface InterceptorAnnotation {
}
