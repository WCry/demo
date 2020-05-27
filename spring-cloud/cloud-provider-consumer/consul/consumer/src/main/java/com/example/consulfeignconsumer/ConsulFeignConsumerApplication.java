package com.example.consulfeignconsumer;

import com.example.consulfeignapi.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class ConsulFeignConsumerApplication {
    @FeignClient("${clientname}")
    interface HelloServiceClient extends HelloService {
    }

    @RestController
    class TestController {
        private final HelloServiceClient helloServiceClient;

        public TestController(HelloServiceClient helloServiceClient) {
            this.helloServiceClient = helloServiceClient;
        }

        @GetMapping("/test")
        public String test(String name) {
            return helloServiceClient.hello(name);
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(ConsulFeignConsumerApplication.class, args);
    }

}
