package com.zxp.controller;

import com.zxp.exception.ResponseEnum;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangxuepei
 * @since 3.0
 */
@RestController
public class TestResponseEnumController {
    @PostMapping(value = "/dd")
    public void Test(){
        // 明确断言哪一个类型的错误 断言完成直接抛出异常   可以很好的实现业务的错误类型操作
        ResponseEnum.LICENCE_NOT_FOUND.assertNotNull(new Object());
    }
}
