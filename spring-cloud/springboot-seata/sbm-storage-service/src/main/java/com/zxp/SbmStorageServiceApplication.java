package com.zxp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(scanBasePackages = "com.zxp", exclude = DataSourceAutoConfiguration.class)
public class SbmStorageServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SbmStorageServiceApplication.class, args);
    }

}
