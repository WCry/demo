package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletPath;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

@SpringBootApplication

//(exclude = {DispatcherServletAutoConfiguration.class})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }

//    /**
//     * 需要禁用 / 的 DispatcherServlet
//     * @return
//     */
//    @Bean
//    public ServletRegistrationBean restServlet(){
//        //注意DispatcherServletRegistrationBean 是Springboot使用的
//        //这里注册的 Servelt 能够拦截对应包中的Controller
//        //这里注册多个 DispatcherServlet 这些路径都能够对应静态资源的访问
//        //注解扫描上下文
//        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
//
//        //扫描对应的包
//        applicationContext.scan("demo.controller");
//        //通过构造函数指定dispatcherServlet的上下文
//        DispatcherServlet rest_dispatcherServlet = new DispatcherServlet(applicationContext);
//        //用ServletRegistrationBean包装servlet
//        ServletRegistrationBean registrationBean = new ServletRegistrationBean(rest_dispatcherServlet);
//        registrationBean.setLoadOnStartup(1);
//        //指定urlmapping
//        registrationBean.addUrlMappings("/rest/*");
//        //指定name，如果不指定默认为dispatcherServlet
//        registrationBean.setName("rest");
//        return registrationBean;
//    }

}
