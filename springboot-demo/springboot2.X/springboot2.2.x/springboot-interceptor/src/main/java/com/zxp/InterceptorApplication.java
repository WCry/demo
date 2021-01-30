package com.zxp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
//用于导入自定义XML中定义的Bean对象
//import 用于导入自定义类中配置的 ImportBeanDefinitionRegistrar
//@ImportResource("")
public class InterceptorApplication {

    public static void main(String[] args) {
        SpringApplication.run(InterceptorApplication.class, args);
    }

}
