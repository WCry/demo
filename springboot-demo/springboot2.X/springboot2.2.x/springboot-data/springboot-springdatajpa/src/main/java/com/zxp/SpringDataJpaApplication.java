package com.zxp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.text.MessageFormat;

//开启JPA仓库
//https://www.cnblogs.com/yihuihui/p/11071949.html
@EnableJpaRepositories
@SpringBootApplication
@EnableTransactionManagement
public class SpringDataJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringDataJpaApplication.class, args);
    }

}
