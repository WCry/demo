package com.zxp.controller;

import com.zxp.validatedata.ValidatedParams;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
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
