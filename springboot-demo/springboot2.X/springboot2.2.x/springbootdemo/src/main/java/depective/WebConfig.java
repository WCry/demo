package depective;//package com.example.demo;
//
//import org.springframework.boot.SpringBootConfiguration;
//import org.springframework.context.annotation.Bean;
//import org.springframework.http.converter.HttpMessageConverter;
//import org.springframework.http.converter.StringHttpMessageConverter;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
//import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
//
//import java.nio.charset.Charset;
//import java.util.List;
//
///*
//*
// * create by jack 2018/8/19
// *
// * @auther jack
// * @date: 2018/8/19 10:50
// * @Description:
//*/
//
//
////@SpringBootConfiguration
//public class WebConfig extends WebMvcConfigurationSupport {
//    @Bean
//    public HttpMessageConverter<String> responseBodyConverter() {
//        return new StringHttpMessageConverter(Charset.forName("UTF-8"));
//    }
//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        super.configureMessageConverters(converters);
//        converters.add(responseBodyConverter());
//    }
//
//    @Override
//    public RequestMappingHandlerMapping requestMappingHandlerMapping() {
//        //return super.requestMappingHandlerMapping();
//        RequestMappingHandlerMapping handlerMapping = new CustomRequestMappingHandlerMapping();
//        handlerMapping.setOrder(0);
//        return handlerMapping;
//    }
//
//}
