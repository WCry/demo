package com.zxp.springaop;

/**
 * @author zhangxuepei
 * @since 3.0
 */
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class MyMethodInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        System.out.println("MyMethodInterceptor  "+methodInvocation.getMethod().getName());
        return methodInvocation.proceed();
    }
}
