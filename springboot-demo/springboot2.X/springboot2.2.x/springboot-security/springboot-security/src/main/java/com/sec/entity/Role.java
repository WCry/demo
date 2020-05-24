package com.sec.entity;


import org.springframework.security.core.GrantedAuthority;

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

    /**
     * 获取权限
     * @return
     */
    @Override
    public String getAuthority() {
        return name;
    }

}
