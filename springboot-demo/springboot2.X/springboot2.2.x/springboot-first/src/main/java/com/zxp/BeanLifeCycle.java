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
 * Bean的声明感知流程
 */
@Component
public class BeanLifeCycle implements BeanNameAware, BeanPostProcessor, InitializingBean,
        DisposableBean, BeanFactoryAware, BeanFactoryPostProcessor {
    private String name;
    //测试是用@Autowired定义的初始化方法
    @Autowired
    public void testIniMethod(){
        System.out.println("Autowired 方法执行");
    }

    /**
     * BeanNameAware感知的设置Bean的Name
     * @param name
     */
    @Override
    public void setBeanName(String name) {
        System.out.println("BeanNameAware " + "设置对象的Name"+name);
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
        //在这里可以实现Bean 对象上注解的处理  所有的Bean对象都会经过这里
        System.out.println("BeanPostProcessor " + "处理在构造对象在Bean的初始化之前");
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
        //在这里可以实现Bean 对象上注解的处理  所有的Bean对象都会经过这里
        // 这里也可以
        System.out.println("处理在构造对象之后");
        return bean;
    }

    /**
     * Bean 释放掉
     * @throws Exception
     */
    @Override
    public void destroy() throws Exception {
        System.out.println(" " +
                "程序结束，DisposableBean" +
                "对象销毁，主要用来处理资源的释放");
    }

    /**
     * 属性设置完成之后
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Bean属性设置之后处理");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("BeanFactoryAware 设置Bean对象的工厂");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("配置Bean对象工厂");
    }
}
