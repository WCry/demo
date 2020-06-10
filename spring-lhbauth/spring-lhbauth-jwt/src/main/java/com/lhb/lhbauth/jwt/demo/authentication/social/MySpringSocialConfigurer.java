package com.lhb.lhbauth.jwt.demo.authentication.social;

import lombok.Data;
import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * @author lvhaibao
 * @description 自定义生成自己的处理的URL
 * @date 2019/1/3 0003 10:27
 */
@Data
public class MySpringSocialConfigurer  extends SpringSocialConfigurer {

    private String filterProcessesUrl;

    MySpringSocialConfigurer(String filterProcessesUrl) {
        this.filterProcessesUrl = filterProcessesUrl;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected <T> T postProcess(T object) {
        SocialAuthenticationFilter filter = (SocialAuthenticationFilter) super.postProcess(object);
        filter.setFilterProcessesUrl(filterProcessesUrl);

        return (T) filter;
    }
}
