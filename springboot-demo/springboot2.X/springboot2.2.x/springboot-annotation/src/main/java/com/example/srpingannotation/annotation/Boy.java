package com.example.srpingannotation.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @author zhangxuepei
 * @since 3.0
 */
@Target({ElementType.FIELD}) //声明应用在属性上
@Retention(RetentionPolicy.RUNTIME) //运行期生效
@Documented //Java Doc 说明
@Component
public @interface Boy {
    String value() default "";
}
