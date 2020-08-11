package com.zxp.controller;

import com.zxp.entry.AbstractTestValidatedParams;
import com.zxp.entry.TestValidatedParams;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangxuepei
 * @since 3.0
 */
@RestController
public class TestParamsValidatedController {
    @GetMapping("/test/params/validated100")
    public void testValidated(@Validated(AbstractTestValidatedParams.Groups.groups100.class) TestValidatedParams testValidatedParams){
        System.out.println("参数校验通过"+testValidatedParams.getNumbers());

    }

    @GetMapping("/test/params/validated1000")
    public void testValidated1000(@Validated(AbstractTestValidatedParams.Groups.groups1000.class) TestValidatedParams testValidatedParams){
        System.out.println("参数校验通过"+testValidatedParams.getNumbers());

    }
}
