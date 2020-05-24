package spring.study.refresh.context.beanprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * Bean传递处理
 *     注解进行拦截处理
 *     Object bean 是Bean 对象
 */
@Component
public class MyBeanPostProcessor implements BeanPostProcessor {

    public MyBeanPostProcessor() {
        System.out.println("------ MyBeanPostProcessor construct");
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String arg1)
            throws BeansException {
        System.out.println(arg1);
        //Bean 定义处理前
        System.out.println("------ MyBeanPostProcessor before initialization");
        System.out.println("Bean实体初始化前，创建对象还没有初始化："+bean);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String arg1)
            throws BeansException {
        System.out.println(arg1);
        //Bean 定义处理后
        System.out.println("------ MyBeanPostProcessor after initialization");
        System.out.println("Bean实体初始化后："+bean);
        return bean;
    }
}
