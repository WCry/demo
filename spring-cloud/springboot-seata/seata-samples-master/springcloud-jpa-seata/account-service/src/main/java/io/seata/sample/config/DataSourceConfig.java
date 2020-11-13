package io.seata.sample.config;

import com.alibaba.druid.pool.DruidDataSource;
import io.seata.rm.datasource.DataSourceProxy;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * 数据源配置
 *
 * @author HelloWoodes
 */
@Configuration
public class DataSourceConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DruidDataSource druidDataSource() {
        return new DruidDataSource();
    }


//    /**
//     * 需要将 DataSourceProxy 设置为主数据源，否则事务无法回滚
//     *   采用自动配置了所以这里不能再设置了SeataDataSourceBeanPostProcessor中进行处理主要判断Bean类型
//     *   通过直接在postProcessAfterInitialization方法中拦截Bean然后对于进行处理返回代理的数据源类
//     *
//     * @param druidDataSource The DruidDataSource
//     * @return The default datasource
//     */
//    @Primary
//    @Bean
//    public DataSource dataSource(DruidDataSource druidDataSource) {
//        return new DataSourceProxy(druidDataSource);
//    }
}
