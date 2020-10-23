//package com.example.consulfeignconsumer;
//
//import org.springframework.cloud.client.loadbalancer.LoadBalancerInterceptor;
//import org.springframework.cloud.client.loadbalancer.RestTemplateCustomizer;
//import org.springframework.http.client.ClientHttpRequestInterceptor;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @author zhangxuepei
// * @since 3.0
// */
//public class TestA {
//    public static void main(String[] args) {
//        RestTemplate restTemplate;
//        RestTemplateCustomizer restTemplateCustomizer=TestA.restTemplateCustomizer(null);
//        System.out.println(restTemplateCustomizer);
//        System.out.println("dsad");
//    }
//
//    public static RestTemplateCustomizer restTemplateCustomizer(final LoadBalancerInterceptor loadBalancerInterceptor) {
//        System.out.println("dsaddasd");
//       return dd->{};
////        return dd -> {
////            System.out.println("dsaaaaaaaaa");
////            List<ClientHttpRequestInterceptor> list = new ArrayList<>(dd.getInterceptors());
////            list.add(loadBalancerInterceptor);
////            dd.setInterceptors(list);
////            System.out.println(dd.getInterceptors().size());
////        };
//    }
//}
