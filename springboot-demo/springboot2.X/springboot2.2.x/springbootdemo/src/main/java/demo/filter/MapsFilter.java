package demo.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * URL映射过滤器
 */

@Component
@WebFilter(urlPatterns = {"/my2/spring/boot/*"}, filterName = "urlMappingFilter")
public class MapsFilter implements Filter {
    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        if (request.getRequestURI().contains("/rest/spring")) {
            //对于特定URL进行过滤，重定向
            req.getRequestDispatcher(request.getRequestURI().replace("/rest/spring", "")).
                    forward(req, response);
        } else {
            //直接进行放行处理URL
            filterChain.doFilter(request, response);
        }
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {

    }
}

