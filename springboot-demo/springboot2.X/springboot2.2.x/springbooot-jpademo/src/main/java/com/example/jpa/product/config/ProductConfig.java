package com.example.jpa.product.config;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.example.jpa.config.AtomikosJtaPlatform;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Map;

//注册产品的数据库
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "productEntityManagerFactory",
        transactionManagerRef = "transactionManager",
        basePackages = {"com.example.jpa.product.repository"}
)
public class ProductConfig {
    @Resource
    private JpaProperties jpaProperties;

    @Resource
    private HibernateProperties properties;


    @Bean(name = "productDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.product")
    public DataSource dataSource() {
        AtomikosDataSourceBean xaDataSourceBean = new AtomikosDataSourceBean();
        return xaDataSourceBean;
    }

    @Bean(name = "productEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean barEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("productDataSource") DataSource dataSource) {
        Map<String, Object> jtpaProperties =
                properties.determineHibernateProperties(jpaProperties.getProperties(),
                new HibernateSettings());
        LocalContainerEntityManagerFactoryBean  entityManager=builder.
                dataSource(dataSource).packages("com.example.jpa.product.models").
                //需要分布式事务管理 开启jta 分布式事务
                persistenceUnit("product").jta(true).
                properties(jtpaProperties).build();
        return entityManager;
    }

    /**
     * 这个是一个简单的jpa事务管理  使用
     */
//    @Bean(name = "productTransactionManager")
//    public PlatformTransactionManager productTransactionManager(
//            @Qualifier("productEntityManagerFactory") EntityManagerFactory productEntityManagerFactory) {
//        return new JpaTransactionManager(productEntityManagerFactory);
//    }
}
