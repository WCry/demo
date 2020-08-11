package com.zxp.springbootfirstapplication;

import com.zxp.controller.TestParamsValidatedController;
import com.zxp.entry.TestValidatedParams;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestParamValidated {
    @Autowired
    private TestParamsValidatedController testParamsValidatedController;
    @Test
    public void contextLoads() {
        TestValidatedParams testValidatedParams=new TestValidatedParams();
        testValidatedParams.setNumbers(10000);
        testParamsValidatedController.testValidated(testValidatedParams);
    }
}
