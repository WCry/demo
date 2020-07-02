//package com.crhms.security.authorizationserver.config;
//
///**
// * user:zxp
// * Day:2020,05,10
// **/
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//public class LogInterceptor implements HandlerInterceptor {
//    private static Logger logger = LoggerFactory.getLogger(LogInterceptor.class);
//    /**
//     * 执行拦截器之前
//     */
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
//            throws Exception {
//        logger.info("interceptor....在执行前...url:{}", request.getRequestURL());
//        String user = (String)request.getSession().getAttribute("user");
//        if(user==null){
//            response.sendRedirect("/myweb/login");
//        }
//        return true; //返回false将不会执行了
//    }
//
//    /**
//     * 调用完处理器，渲染视图之前
//     */
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
//                           ModelAndView modelAndView) throws Exception {
//        logger.info("interceptor.......url:{}", request.getRequestURL());
//    }
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
//            throws Exception {
//    }
//}