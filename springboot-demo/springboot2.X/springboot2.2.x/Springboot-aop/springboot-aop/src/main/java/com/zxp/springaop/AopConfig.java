//package com.zxp.springaop;
//
//import org.springframework.aop.aspectj.AspectJExpressionPointcut;
//import org.springframework.aop.support.DefaultPointcutAdvisor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * @author zhangxuepei
// * @since 3.0
// */
//@Configuration
//public class AopConfig {
//    public static final String traceExecution = "execution(* com.wang.chao.micro..*.*(..))";
//    public static final String traceAnnotationExecution = "@annotation(com.wang.chao.micro.interceptor.annotation.InterceptorAnnotation)";
//    /**
//     * 采用ApectJ的方式注入
//     * @return
//     */
//    @Bean
//    public DefaultPointcutAdvisor defaultPointcutAdvisor(){
//        MyMethodInterceptor methodInterceptor = new MyMethodInterceptor();
//        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
//        pointcut.setExpression(traceExecution);
//        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
//        //通过Java的JDK动态代理注入
////        JdkRegexpMethodPointcut pointcut2 = new JdkRegexpMethodPointcut();
////        pointcut2.setPattern("com.wang.chao.micro.*");
////        advisor.setPointcut(pointcut2);
//        //注入自定义注解解析
////        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
////        pointcut.setExpression(traceAnnotationExecution);
////        advisor.setPointcut(pointcut);
////        advisor.setAdvice(methodInterceptor);
//    //    advisor.setAdvice(methodInterceptor);
//        //case 通过注解匹配的方式埋入切点
////        AnnotationMatchingPointcut pointcut = new AnnotationMatchingPointcut(null, InterceptorAnnotation.class);
////        advisor.setPointcut(pointcut);
////        advisor.setAdvice(methodInterceptor);
//        advisor.setPointcut(pointcut);
//        advisor.setAdvice(methodInterceptor);
//        return advisor;
//    }
//}
