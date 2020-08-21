package com.zxp.scope;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

/**
 * @author zhangxuepei
 * @since 3.0
 * 可以针对一个Seesion请求创建一个对象
 * 只在同一个Session是同一个对象
 */
@Component
@SessionScope
public class SessionScopeAction {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
