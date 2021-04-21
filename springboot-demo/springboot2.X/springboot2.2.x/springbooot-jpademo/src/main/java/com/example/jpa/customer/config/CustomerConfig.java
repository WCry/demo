package com.example.jpa.customer.config;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.example.jpa.config.AtomikosJtaPlatform;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "customerEntityManagerFactory",
        transactionManagerRef = "transactionManager",
        basePackages = {"com.example.jpa.customer.repository"})
public class CustomerConfig {
    @Resource
    private JpaProperties jpaProperties;

    @Resource
    private HibernateProperties properties;

    @Primary
    @Bean(name = "customerDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.customer")
    public DataSource customerDataSource() {
        // 将本地事务注册到创 Atomikos全局事务
        AtomikosDataSourceBean xaDataSourceBean = new AtomikosDataSourceBean();
        return xaDataSourceBean;
    }

    @Primary
    @Bean(name = "customerEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("customerDataSource") DataSource dataSource) {
        Map<String, Object> jtpaProperties =
                properties.determineHibernateProperties(jpaProperties.getProperties(),
                        new HibernateSettings());
//        jtpaProperties.put("hibernate.transaction.jta.platform", AtomikosJtaPlatform.class.getName());
//        jtpaProperties.put("javax.persistence.transactionType", "JTA");
        LocalContainerEntityManagerFactoryBean  entityManager=builder.dataSource(dataSource).
                //设置主体位置
                        packages("com.example.jpa.customer.models").
                        persistenceUnit("customer").
                        properties(jtpaProperties).
                        build();;
        return entityManager;
    }

//    @Primary
//    @Bean(name = "customerTransactionManager")
//    public PlatformTransactionManager customerTransactionManager(
//            @Qualifier("customerEntityManagerFactory") EntityManagerFactory customerEntityManagerFactory) {
//        return new JpaTransactionManager(customerEntityManagerFactory);
//    }
}
