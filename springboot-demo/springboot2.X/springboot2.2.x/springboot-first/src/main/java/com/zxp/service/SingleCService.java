package com.zxp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author zhangxuepei
 * @since 3.0
 */
@Component
public class SingleCService {
    /**
     * 采用直接注入方式，获取到的对象始终是一个对象
     * 可以和A服务的中方法进行对比
     */
    final ScopeBInstance scopeBInstance;

    @Autowired
    public SingleCService(ScopeBInstance scopeBInstance) {this.scopeBInstance = scopeBInstance;}

    public String getName() {
        return scopeBInstance.getName();
    }

    public void setName(String name) {
        scopeBInstance.setName(name);
    }
}
