package com.example.consulfeignapi;

import feign.Contract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.AnnotatedParameterProcessor;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.support.FormattingConversionService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangxuepei
 * @since 3.0
 * https://blog.csdn.net/u014527058/article/details/79396998
 */
@Configuration
public class MyFeignClientsConfiguration {

    @Autowired(required = false)
    private List<AnnotatedParameterProcessor> parameterProcessors = new ArrayList<>();

    @Bean
    public Contract feignContract(FormattingConversionService feignConversionService) {
        //在原配置类中是用ConversionService类型的参数，但ConversionService接口不支持addConverter操作，使用FormattingConversionService仍然可以实现feignContract配置。
        feignConversionService.addConverter(new UniversalReversedEnumConverter());
        return new SpringMvcContract(this.parameterProcessors, feignConversionService);
    }
}
