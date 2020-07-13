package com.zxp.service;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author zhangxuepei
 * @since 3.0
 */
@Component
//采用原型模式
@Scope(value= ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ScopeBInstance {
    private String name="1";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
