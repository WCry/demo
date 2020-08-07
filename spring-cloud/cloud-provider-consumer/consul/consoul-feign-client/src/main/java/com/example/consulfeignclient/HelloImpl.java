package com.example.consulfeignclient;

import com.example.consulfeignapi.Gender;
import com.example.consulfeignapi.HelloService;
import com.example.consulfeignapi.Language;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author zhangxuepei
 * @since 3.0
 */
@RestController
public class HelloImpl implements HelloService {
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

    @Override
    public Gender testBodyAndQueryParams(byte[] bytes, Map<String, Object> requestParams) {
        System.out.println(bytes.length);
        System.out.println(requestParams.get("test"));
        return null;
    }

//    @Override
//    public Gender testBodyAndQueryParams(String name, Map<String, Object> requestParams) {
//        System.out.println(requestParams.get("test"));
//        return null;
//    }
}
