package com.example.jpa.config;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.transaction.UserTransaction;


@Configuration
public class JtaTransactionManagerConfig {
    /**
     * JTA的分布式事务管理 分布式事务的解决方案
     * SpringBoot实现 当前JTA是单机的分布式事务管理
     * @return
     */
    @Primary
    @Bean(name = "jtaTransactionManager")
    public JtaTransactionManager regTransactionManager () {
        UserTransactionManager userTransactionManager = new UserTransactionManager();
        UserTransaction userTransaction = new UserTransactionImp();
        JtaTransactionManager jtaTransactionManager=new JtaTransactionManager(userTransaction
                ,userTransactionManager);
        //设置缓存用户的事务
        //jtaTransactionManager.setCacheUserTransaction(true);
        return jtaTransactionManager;
    }
}
