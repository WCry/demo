//package com.zxp.gatway.demo;
//
//import org.springframework.cloud.gateway.route.RouteLocator;
//import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.stereotype.Component;
//
///**
// * user:zxp
// * Day:2020,06,08
// **/
//@Component
//public class RouteConfig {
//    @Bean
//    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
//        return builder.routes()
//                .route(r -> r.path("/activity/**")
//                        .filters(f -> f.stripPrefix(1).addResponseHeader("X-Response-Default-Foo",
//                                "Default-Bar"))
//                        .uri("lb://activity")
//                        .order(0)
//                        .id("activity-route")
//                )
//                .build();
//    }
//}
