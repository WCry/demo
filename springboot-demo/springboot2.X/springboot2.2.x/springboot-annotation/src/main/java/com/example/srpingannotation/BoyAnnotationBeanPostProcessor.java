package com.example.srpingannotation;

/**
 * @author zhangxuepei
 * @since 3.0
 */
import com.example.srpingannotation.annotation.Boy;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Component //注意：Bean后置处理器本身也是一个Bean
public class BoyAnnotationBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        /**
         * 利用Java反射机制  获取类属性字段
         */
        Field[] declaredFields = bean.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            //获取字段是否包含有Boy注解
            Boy annotation = declaredField.getAnnotation(Boy.class);
            if (null == annotation) {
                continue;
            }
            //设置类的获取 虽然name是私有字段
            declaredField.setAccessible(true);
            try {
                //设置字段对应的名称
                declaredField.set(bean, annotation.value());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return bean;
    }

//    @Override
//    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
//        return o;
//    }
}
