package com.zxp;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * @author zhangxuepei
 * @since 3.0
 */
@Component
public class BeanLife implements BeanNameAware, BeanPostProcessor, InitializingBean,
        DisposableBean, BeanFactoryAware, BeanFactoryPostProcessor {
    private String name;
    @Autowired
    public void testIni(){
        System.out.println("Autowired 方法执行");
    }

    /**
     * 设置Bean的Name
     * @param name
     */
    @Override
    public void setBeanName(String name) {
        System.out.println("设置对象的Name"+name);
    }

    /**
     * Bean 构造前
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Nullable
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("处理在构造对象在Bean的初始化之前");
        return bean;
    }

    /**
     * Bean 构造后
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Nullable
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("处理在构造对象之后");
        return bean;
    }

    /**
     * Bean 释放掉
     * @throws Exception
     */
    @Override
    public void destroy() throws Exception {
        System.out.println("程序结束，对象销毁");
    }

    /**
     * 属性设置完成之后
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("属性设置之后处理");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("设置Bean对象的工厂");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("配置Bean对象工厂");
    }
}
