package com.crhms.security.authorizationserver.config;


import com.crhms.security.authorizationserver.security.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
//@Order(1)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * 授权拦截的路径 addPathPatterns：拦截的路径 excludePathPatterns：不拦截的路径
     */

    public final UserDetailService userDetailService;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityConfig(UserDetailService userDetailService, PasswordEncoder passwordEncoder) {
        this.userDetailService = userDetailService;
        this.passwordEncoder = passwordEncoder;
    }

//    @Override
//    @Bean
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception { // @formatter:off
        http.csrf().disable();
//        http.requestMatchers()
//                .antMatchers("/login", "/oauth/authorize", "/ssoLogout")
//                .and()
//                .authorizeRequests().anyRequest().authenticated()
//                .and()
//                .formLogin().loginPage("/login").permitAll()
//                .and()
//                .logout().logoutUrl("/logout").logoutSuccessUrl("/login");
        http.formLogin()
                //展示登录界面的URL
                .loginPage("/aa/login")
                //配置登录处理的URL 在认证中心的登录界面 登录表单的提交地方
                .loginProcessingUrl("/authentication/form")
                .and()
                .authorizeRequests()
                .antMatchers("/aa/login",
                        "/authentication/form",
                        "/**/*.js",
                        "/**/*.css",
                        "/**/*.jpg",
                        "/**/*.png",
                        "/**/*.woff2"
                )
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable();



    }

//    public AuthenticationProcessingFilter authenticationProcessingFilter() throws Exception {
//        AuthenticationProcessingFilter filter = new AuthenticationProcessingFilter();
//        filter.setAuthenticationManager(authenticationManagerBean());
//        return filter;
//    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception { // @formatter:off
        auth.userDetailsService(userDetailService);//.passwordEncoder(passwordEncoder);
    }

//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        //解决静态资源被拦截的问题
//        web.ignoring().antMatchers("/css/**",
//                "/js/**",
//                "/image/**",
//                "/favicon.ico");
//    }
}
