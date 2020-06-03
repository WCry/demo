package com.example.consulfeignapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public interface HelloService {
    @GetMapping("/hello")
    String hello(@RequestParam(value = "name") String name);

    @GetMapping("/gender")
    Gender getGender(@RequestParam(value = "name") String name);
}
