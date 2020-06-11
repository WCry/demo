package com.zxp.controller;

import com.zxp.dto.DtoGroup;
import com.zxp.dto.UserDTO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.groups.Default;

/**
 * @author zhangxuepei
 * @since 3.0
 */
@RestController
public class TestValidated {
    //注意Springboot的 Default.class
    //对于没有分组的注解都放在默认分组当中
    @PostMapping(value = "/testValidated")
    public String vaValue(@Validated({DtoGroup.Update.class, Default.class}) @RequestBody UserDTO userDTO) {
        System.out.println("验证成功");
        return "验证成功";
    }
}
