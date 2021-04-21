package com.example.jpa.config;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import org.hibernate.engine.transaction.jta.platform.internal.AbstractJtaPlatform;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.transaction.UserTransaction;

@Configuration
public class JtaTransactionManagerConfig {

    @Primary
    @Bean(name = "jtaTransactionManager")
    public JtaTransactionManager regTransactionManager () {
        UserTransactionManager userTransactionManager = new UserTransactionManager();
        UserTransaction userTransaction = new UserTransactionImp();
        JtaTransactionManager jtaTransactionManager=new JtaTransactionManager(userTransaction
                ,userTransactionManager);
        jtaTransactionManager.setCacheUserTransaction(true);
        return jtaTransactionManager;
    }
}
