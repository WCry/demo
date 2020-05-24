package spring.study.componentprovider.annotation;

import org.springframework.context.annotation.Import;
import spring.study.componentprovider.spring.ConsumerRegistrar;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注解方式启动 通过注解类 注入进来
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(ConsumerRegistrar.class)
public @interface EnableConsumer {

}
