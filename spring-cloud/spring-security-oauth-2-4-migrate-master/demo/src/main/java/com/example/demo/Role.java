package com.example.demo;

import org.springframework.security.core.GrantedAuthority;

/**
 * user:zxp
 * Day:2020,03,29
 *   实现安全验证中权限的管理
 **/
public class Role implements GrantedAuthority {

    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return name;
    }

}
