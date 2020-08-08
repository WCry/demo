package spring.study.startup.initializers;


import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import spring.study.startup.bean.SimpleBean;

/**
 * 创建Context 监听事件
 * 在resource/META—INF的spring.factories
 * 采用SPI方式进行注入对象
 */
public class SimpleApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {

        if(applicationContext instanceof AnnotationConfigServletWebServerApplicationContext) {
            System.out.println("我监听的是ApplicationContextInitializer的AnnotationConfigServletWebServerApplicationContext" +
                    "application初始化");
            //通过一个Bean工厂注册一个单实例对象
            //Bean的name和Bean的对象
            ((AnnotationConfigServletWebServerApplicationContext)applicationContext).getBeanFactory().registerSingleton("testBean",
                    new SimpleBean("testBean", "created by initializer"));
        }
    }
}
