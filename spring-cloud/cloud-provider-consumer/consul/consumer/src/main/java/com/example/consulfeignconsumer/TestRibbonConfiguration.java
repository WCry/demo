package com.example.consulfeignconsumer;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhangxuepei
 * @since 3.0
 */
@Configuration
@RibbonClient(name="consumer", configuration=RibbonConfiguration.class)
public class TestRibbonConfiguration {
}
