package com.example.consulfeignapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


public interface HelloService {
    @GetMapping("/hello")
    String hello(String name);
    @PostMapping("/hello22")
    String hello22();
    @PostMapping("/gender")
    Gender getGender(String name);
}
