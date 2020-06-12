package com.zxp.soo.client.config;

import com.zxp.sso.mobile.MobileCodeAuthenticationProvider;
import com.zxp.sso.mobile.UsernamePasswordAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.client.RestTemplate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * -
 * [简要描述]: 配置安全验证适配器
 * [详细描述]:
 */
@Configuration

@EnableOAuth2Sso   //该注解是在Sso的客户端使用  有了sso 这些可以省略
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//@EnableOAuth2Client  这个是是 单独使用Oath2的时候使用 在sso模式不使用
public class SsoClient2Config extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
       //应用配置 自定义安全配置  SecurityConfigurerAdapter  配置自定义的Se
       //  http.apply()  通过配置文件处理
      //  http.securityContext(null);
       // http.authenticationProvider(mobileCodeAuthenticationProvider());
//        http.logout().logoutUrl("/logout").logoutSuccessHandler(new logoutSuccessHandler())
//                .permitAll();
    }

    public UsernamePasswordAuthenticationProvider usernamePasswordAuthenticationProvider() {
        return new UsernamePasswordAuthenticationProvider();
    }


    public MobileCodeAuthenticationProvider mobileCodeAuthenticationProvider() {
        return new MobileCodeAuthenticationProvider();
    }

    class logoutSuccessHandler implements LogoutSuccessHandler {

        @Autowired
        RestTemplate restTemplate;

        @Override
        public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        }
    }
}
