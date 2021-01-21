package com.wyj.config;

/**
 * @author zhangxuepei
 * @since 3.0
 */
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println(" myfilter init");
    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //在DispatcherServlet之前执行
        System.out.println("############TestFilter1 doFilterInternal executed############");
        filterChain.doFilter(servletRequest, servletResponse);
        //在视图页面返回给客户端之前执行，但是执行顺序在Interceptor之后
        System.out.println("############TestFilter1 doFilter after############");

    }
    @Override
    public void destroy() {
        System.out.println("myfilter destroy");
    }
}
