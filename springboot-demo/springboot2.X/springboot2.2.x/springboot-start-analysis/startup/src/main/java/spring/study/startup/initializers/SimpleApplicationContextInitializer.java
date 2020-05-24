package spring.study.startup.initializers;

import org.springframework.boot.context.embedded.AnnotationConfigEmbeddedWebApplicationContext;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import spring.study.startup.bean.SimpleBean;

/**
 * 创建Context 监听事件
 */
public class SimpleApplicationContextInitializer implements ApplicationContextInitializer
        <ConfigurableApplicationContext> {
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        if(applicationContext instanceof AnnotationConfigEmbeddedWebApplicationContext) {
            System.out.println("我监听的是ApplicationContextInitializer的AnnotationConfigEmbeddedWebApplicationContext" +
                    "application初始化");
            //通过一个Bean工厂注册一个单实例对象
            //Bean的name和Bean的对象
            ((AnnotationConfigEmbeddedWebApplicationContext)applicationContext).
                    getBeanFactory().registerSingleton("testBean",
                    new SimpleBean("id-001", "created by initializer"));
        }
    }
}
