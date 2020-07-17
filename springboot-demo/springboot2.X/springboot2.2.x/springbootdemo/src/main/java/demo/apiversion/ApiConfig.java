package demo.apiversion;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * @author zhangxuepei
 * @since 3.0
 * Swagger请求异常处理 https://blog.csdn.net/sorrow_yc/article/details/88737213
 */
@Configuration
public class ApiConfig extends WebMvcConfigurationSupport {
    /**
     * 获取RequestMappingHandlerMapping
     */
    @Override
    public RequestMappingHandlerMapping requestMappingHandlerMapping() {
        RequestMappingHandlerMapping handlerMapping = new CustomRequestMappingHandlerMapping();
        handlerMapping.setOrder(0);
        handlerMapping.setInterceptors(getInterceptors());
        return handlerMapping;
    }
}
