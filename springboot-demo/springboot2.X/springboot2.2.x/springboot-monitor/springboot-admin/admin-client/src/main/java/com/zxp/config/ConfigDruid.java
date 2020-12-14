package com.zxp.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhangxuepei
 * @since 3.0
 *
 */
@Configuration
public class ConfigDruid {
        @Bean
        @ConfigurationProperties(prefix = "spring.datasource")
        //用于将application.properties中的属性设置到druid中
        public DruidDataSource druidDataSource() {
            return new DruidDataSource();
        }
}
