package com.sec.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * user:zxp
 * Day:2020,04,19
 **/
public class BeforeLoginFilter extends OncePerRequestFilter implements ApplicationContextAware {
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        System.out.println("This is a filter before UsernamePasswordAuthenticationFilter.");
//        System.out.println(servletRequest);
//        // 继续调用 Filter 链
//       // filterChain.doFilter(servletRequest, servletResponse);
//    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
       // System.out.println("This is a filter before UsernamePasswordAuthenticationFilter.");
        System.out.println(httpServletRequest.getServletPath());

    }
   private ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }
}
