package com.example.eurekafeignclient;

import com.example.eurekafeignapi.HelloService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RestController;

@EnableDiscoveryClient
@SpringBootApplication
public class EurekaFeignClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaFeignClientApplication.class, args);
    }
    @RestController
    class HelloController implements HelloService {
        @Override
        public String hello(String name) {
            return "hello " + name;
        }
    }
}
