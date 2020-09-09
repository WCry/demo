package com.zxp.controller;

import com.zxp.SpringbootLearningApplication;
import com.zxp.validatedata.ValidatedParams;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest(classes= SpringbootLearningApplication.class)
public class TestParamValidated {
    @Autowired
    private ParamsValidatedController paramsValidatedController;
    @Test
    public void contextLoads() {
        ValidatedParams testValidatedParams=new ValidatedParams();
        testValidatedParams.setNumbers(10000);
        paramsValidatedController.testValidated(testValidatedParams);
    }
}
