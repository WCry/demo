package com.example.consulfeignconsumer;

import com.example.consulfeignapi.OrderService;
import com.example.consulfeignapi.util.Gender;
import com.example.consulfeignapi.util.Language;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangxuepei
 * @since 3.0
 */
@RestController
public class OrderImpl implements OrderService {
    @Override
    public String hello(String name) {
        return "hello " + name;
    }

    @Override
    public Language countyLanguage(String name) {
        return Language.CHINESE_SIMPLIFIED;
    }


    @Override
    public String hello22() {
        return "hello ";
    }

    @Override
    public Gender getGender(String name) {
        return Gender.MAN;
    }
}
