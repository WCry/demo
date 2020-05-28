package com.lhj.miaosha;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author ：LHJ
 * @date ：2019/7/22 22:54
 * @description：${description}
 */


@SpringBootApplication
public class MainApplication{
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

}
//导出war包
//@SpringBootApplication
//public class MainApplication extends SpringBootServletInitializer{
//
//    public static void main(String[] args)
//    {
//        SpringApplication.run(MainApplication.class, args);
//    }
//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//        return builder.sources(MainApplication.class);
//    }
//
//}

