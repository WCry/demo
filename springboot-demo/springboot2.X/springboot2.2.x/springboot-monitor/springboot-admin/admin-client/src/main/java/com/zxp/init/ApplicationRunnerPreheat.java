package com.zxp.init;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author zhangxuepei
 * @since 3.0
 * 程序完成之后
 */
@Component
public class ApplicationRunnerPreheat implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
       // System.out.println(restTemplate.getForObject("http://localhost:8768/health", String.class,new HashMap<>()));
    }
}
