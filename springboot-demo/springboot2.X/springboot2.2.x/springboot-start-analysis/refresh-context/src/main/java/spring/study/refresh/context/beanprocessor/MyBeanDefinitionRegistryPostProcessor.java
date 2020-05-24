package spring.study.refresh.context.beanprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.AnnotatedGenericBeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;
import spring.study.refresh.context.bean.SimpleBeanInBeanDefinitionRegistryPostProcessor;
import spring.study.refresh.context.bean.SimpleBeanWithDefinitionInBeanDefinitionRegistryPostProcessor;

/**
 * 通过Bean定义注册 注册传递处理 进行注入对象
 */
@Component
public class MyBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor,
        PriorityOrdered {
    /**
     * Bean定义注册传递处理
     */
    public MyBeanDefinitionRegistryPostProcessor() {
        System.out.println("------ MyBeanDefinitionRegistryPostProcessor construct");
    }

    /**
     * Bean定义注册
     * @param registry
     * @throws BeansException
     */
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry)
            throws BeansException {
        registry.registerBeanDefinition("simpleBeanWithDefinitionInBeanDefinitionRegistryPostProcessor",
                new AnnotatedGenericBeanDefinition(SimpleBeanWithDefinitionInBeanDefinitionRegistryPostProcessor.class)
        );
    }

    /**
     * 配置列表Bean工厂
     * @param beanFactory bean工厂
     * @throws BeansException Bean异常
     */
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory)
            throws BeansException {
        beanFactory.registerSingleton("simpleBeanInBeanDefinitionRegistryPostProcessor",
                new SimpleBeanInBeanDefinitionRegistryPostProcessor());
    }

    /**
     * 获取优先级
     * @return
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
