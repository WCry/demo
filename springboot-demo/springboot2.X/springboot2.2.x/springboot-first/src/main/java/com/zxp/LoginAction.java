package com.zxp;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

/**
 * @author zhangxuepei
 * @since 3.0
 */
@Component
//可以直接使用  针对一个Request创建一个对象
//@SessionScope
@RequestScope
public class LoginAction {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
