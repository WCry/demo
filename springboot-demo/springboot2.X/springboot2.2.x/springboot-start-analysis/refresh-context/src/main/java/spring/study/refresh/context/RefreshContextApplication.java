package spring.study.refresh.context;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import spring.study.refresh.context.bean.ImportService;
import spring.study.refresh.context.listener.MyApplicationListener;

/**
 * Springboot 启动类注解 EnableAutoConfiguration和ComponentScan注解合并
 */
@SpringBootApplication
//通过主动注入将类注入进来
@Import(ImportService.class)
//注入自定义属性配置文件
@PropertySources(@PropertySource("classpath:simple.properties"))
//注入资源配置文件 类似原先Spring 通过XML文件配置bean 可以注解
//和XML共同使用  xml配置式原先Spring使用方式
@ImportResource("classpath:import.xml")
public class RefreshContextApplication {
    public static void main(String[] args) {
        SpringApplication sa = new SpringApplication(RefreshContextApplication.class);
        sa.addListeners(new MyApplicationListener());
        sa.run(args);
    }
}
