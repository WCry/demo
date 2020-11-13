package com.example.consulfeignapi;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;


public interface HelloService {

    @GetMapping("/helloBytes")
    ResponseEntity<byte[]> hello2();

    @GetMapping("/hello")
    String hello(String name);

    @PostMapping("/county")
    Language countyLanguage(String name);
    @PostMapping("/hello22")
    String hello22();
    @PostMapping("/gender")
    Gender getGender(String name);

    @PostMapping("/testRequestParams")
    Gender testBodyAndQueryParams(@RequestBody byte[] bytes, @RequestParam Map<String, Object> requestParams);
}
