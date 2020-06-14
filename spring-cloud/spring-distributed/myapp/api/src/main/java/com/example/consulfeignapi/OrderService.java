package com.example.consulfeignapi;

import com.example.consulfeignapi.util.Gender;
import com.example.consulfeignapi.util.Language;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


public interface OrderService {

    @PostMapping("/complete")
    Gender getGender(String name);
}
