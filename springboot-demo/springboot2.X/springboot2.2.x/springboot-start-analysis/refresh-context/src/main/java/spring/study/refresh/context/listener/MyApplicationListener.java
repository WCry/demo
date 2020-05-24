package spring.study.refresh.context.listener;

import org.springframework.boot.context.embedded.AnnotationConfigEmbeddedWebApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import spring.study.refresh.context.bean.SimpleBeanInListener;

/**
 * 应用程序监听
 */
public class MyApplicationListener implements ApplicationListener<ApplicationEvent> {
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if(event instanceof ContextRefreshedEvent) {
            ApplicationContext applicationContext = ((ContextRefreshedEvent) event).getApplicationContext();
            if(applicationContext instanceof AnnotationConfigEmbeddedWebApplicationContext) {
                //在监听处理中注入一个简单Bean对象
                ((AnnotationConfigEmbeddedWebApplicationContext) applicationContext).getBeanFactory().
                        registerSingleton("simpleBeanInListener", new SimpleBeanInListener());
            }
        }
    }
}
