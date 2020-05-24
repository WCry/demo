package spring.study.startup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import spring.study.startup.bean.SimpleBean;

/**
 * Spring 应用程序启动
 */
@SpringBootApplication
public class MySpringApplication {
    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(MySpringApplication.class, args);
        System.out.println("simpleBean初始化了"+applicationContext.getBeanNamesForType(SimpleBean.class).length);
        SimpleBean sb = applicationContext.getBean("simpleBean",SimpleBean.class);
        System.out.println("id: " + sb.id + ", name: " + sb.name);
        SimpleBean simpleBean = applicationContext.getBean("testBean", SimpleBean.class);
        System.out.println("id: " + simpleBean.id + ", name: " + simpleBean.name);
    }
    @Bean
    public SimpleBean getNewSimpleBean(){
        return new SimpleBean("我是Bean注解产生","dddd");
    }
}
