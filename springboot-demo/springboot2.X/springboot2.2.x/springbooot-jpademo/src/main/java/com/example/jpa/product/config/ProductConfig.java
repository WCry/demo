package com.example.jpa.product.config;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

//注册产品的数据库
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "productEntityManagerFactory",
        transactionManagerRef = "productTransactionManager",
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
//        // 将本地事务注册到创 Atomikos全局事务
        AtomikosDataSourceBean xaDataSourceBean = new AtomikosDataSourceBean();
//        xaDataSourceBean.setXaDataSource(xaDataSource);
//        xaDataSourceBean.setUniqueResourceName("product");
        return xaDataSourceBean;
    }
//    @Bean(name = "productDataSource")
//    @ConfigurationProperties(prefix = "spring.datasource.product")
//    public DataSource dataSource() {
//       return DataSourceBuilder.create()
//                // .type(dataSourceProperties.getType())
//                .build();
//    }

    @Bean(name = "productEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean barEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("productDataSource") DataSource dataSource) {
        return builder.dataSource(dataSource).packages("com.example.jpa.product.models").
                persistenceUnit("product").
                properties(properties.determineHibernateProperties(jpaProperties.getProperties(),
                        new HibernateSettings())).build();
    }

    @Bean(name = "productTransactionManager")
    public PlatformTransactionManager productTransactionManager(
            @Qualifier("productEntityManagerFactory") EntityManagerFactory productEntityManagerFactory) {
        return new JpaTransactionManager(productEntityManagerFactory);
    }
}
