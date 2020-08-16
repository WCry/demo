package com.zxp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class Colculate {


    @Autowired
    public Colculate() {

    }

    @GetMapping(value = "/string/long")
    public String test() {
        String dsad = UUID.randomUUID().toString();
        Long d=String2Long(dsad);
        return d.toString();
    }

    private Long String2Long(String value) {
        char[] chars = value.toCharArray();
        long h = 0;
        if (h == 0 && chars.length > 0) {
            char val[] = chars;
            for (int i = 0; i < chars.length; i++) {
                h = 31 * h + val[i];
            }
        }
        return h;
    }

}
