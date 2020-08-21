package com.zxp.controller;

import com.zxp.validatedata.AbstractValidatedParams;
import com.zxp.validatedata.ValidatedParams;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangxuepei
 * @since 3.0
 */
@RestController
public class ParamsValidatedController {
    @GetMapping("/test/params/validated100")
    public void testValidated(@Validated(AbstractValidatedParams.Groups.groups100.class) ValidatedParams testValidatedParams){
        System.out.println("参数校验通过"+testValidatedParams.getNumbers());

    }

    @GetMapping("/test/params/validated1000")
    public void testValidated1000(@Validated(AbstractValidatedParams.Groups.groups1000.class) ValidatedParams testValidatedParams){
        System.out.println("参数校验通过"+testValidatedParams.getNumbers());

    }
}
