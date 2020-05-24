package depective;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * URL映射过滤器
 * @author pengjunlee
 * @date 2017年11月23日上午11:31:52
 * */

@Component
@WebFilter(urlPatterns = { "/my2/spring/boot/*"}, filterName = "urlMappingFilter")
public class MapsFilter implements Filter {

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;

        if(request.getRequestURI().contains("/rest/spring"))
        {
            System.out.println("/rest/spring");
            req.getRequestDispatcher(request.getRequestURI().replace("/rest/spring","")).
                    forward(req, response);
        }
        else
        {
            filterChain.doFilter(request, response);
        }
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {

    }

}

