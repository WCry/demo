package com.example.consulfeignclient;


import com.example.consulfeignapi.HelloService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RestController;

@EnableDiscoveryClient
@SpringBootApplication
public class ConsulFeignClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsulFeignClientApplication.class, args);
    }
    @RestController
    class HelloController implements HelloService {
        @Override
        public String hello(String name) {
            return "hello " + name;
        }
    }
}
