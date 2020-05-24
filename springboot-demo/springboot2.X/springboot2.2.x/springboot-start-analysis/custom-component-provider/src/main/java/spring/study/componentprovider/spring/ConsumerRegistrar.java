package spring.study.componentprovider.spring;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.AssignableTypeFilter;
import spring.study.componentprovider.annotation.Consumer;
import spring.study.componentprovider.interfaze.IConsumer;

import java.util.Set;

/**
 * 自定义注入类
 */
public class ConsumerRegistrar implements ImportBeanDefinitionRegistrar{
    private BeanNameGenerator beanNameGenerator = new AnnotationBeanNameGenerator();

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata,
                                        BeanDefinitionRegistry registry) {
        ClassPathScanningCandidateComponentProvider provider = new
                ClassPathScanningCandidateComponentProvider(false);
        provider.addIncludeFilter(new AnnotationTypeFilter(Consumer.class));
        provider.addIncludeFilter(new AssignableTypeFilter(IConsumer.class));
        //扫描对应包下面的类
        Set<BeanDefinition> beanDefinitionSet = provider.findCandidateComponents(
                "spring.study.componentprovider.bean");
        for(BeanDefinition beanDefinition : beanDefinitionSet) {
            String beanName = beanNameGenerator.generateBeanName(beanDefinition, registry);
            if(!registry.containsBeanDefinition(beanName)) {
                registry.registerBeanDefinition(beanName, beanDefinition);
                registry.registerBeanDefinition("11", beanDefinition);
            }
        }
    }
}

