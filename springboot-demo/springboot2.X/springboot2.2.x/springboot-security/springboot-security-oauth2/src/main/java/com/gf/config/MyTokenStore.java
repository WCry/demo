package com.gf.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * user:zxp
 * Day:2020,03,31
 *  JBCTokenStore 实现token的查询存储数据库sql
 **/
@Component
public class MyTokenStore extends JdbcTokenStore{
    @Autowired
    public MyTokenStore(DataSource dataSource) {
        super(dataSource);
    }
}
