package com.zxp.sericefeign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ServiceFeignApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceFeignApplication.class, args);

    }

    /* @Bean
    public IRule feignRulesas() {
        return new MyRule();
    }
    @Bean
    public ServerListUpdater ribbonServerListUpdater() {
        return new EurekaNotificationServerListUpdater();
    }*/

}
