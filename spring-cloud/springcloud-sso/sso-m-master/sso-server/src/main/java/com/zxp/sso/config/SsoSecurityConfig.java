package com.zxp.sso.config;


import com.zxp.sso.mobile.PhoneCodeSecurityConfig;
import com.zxp.sso.socialconfig.SocialOAuth2AccessTokenResponseClient;
import com.zxp.sso.socialconfig.SocialOAuth2UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;


@Configuration
public class SsoSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final SocialOAuth2AccessTokenResponseClient socialOAuth2AccessTokenResponseClient;
    private final PhoneCodeSecurityConfig messageCodeSecurityConfig;
    private final SocialOAuth2UserService socialOAuth2UserService;
   // private final SocialOAuth2AuthorizationRequestResolver SocialOAuth2AuthorizationRequestResolver;
    @Autowired
    public SsoSecurityConfig(@Qualifier("ssoUserDetailsService") UserDetailsService userDetailsService,
                             SocialOAuth2AccessTokenResponseClient socialOAuth2AccessTokenResponseClient,
                             SocialOAuth2UserService socialOAuth2UserService,
                             PhoneCodeSecurityConfig messageCodeSecurityConfig)
                          //   SocialOAuth2AuthorizationRequestResolver SocialOAuth2AuthorizationRequestResolver)
    {
        this.userDetailsService = userDetailsService;
        this.socialOAuth2AccessTokenResponseClient = socialOAuth2AccessTokenResponseClient;
        this.socialOAuth2UserService = socialOAuth2UserService;
        this.messageCodeSecurityConfig = messageCodeSecurityConfig;
   //     this.SocialOAuth2AuthorizationRequestResolver = SocialOAuth2AuthorizationRequestResolver;
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().loginPage("/authentication/require").and().authorizeRequests()
                .antMatchers("/message").permitAll().and().authorizeRequests()
                .antMatchers("/hello").hasAuthority("ROLE_USER");
        //进行电话验证码处理
        http.apply(messageCodeSecurityConfig);
        http.csrf().disable();
//        http. oauth2Login().loginPage("/authentication/require").tokenEndpoint().and().authorizationEndpoint().
//                baseUri("").authorizationRequestResolver(null);

        http. oauth2Login().loginPage("/authentication/require").tokenEndpoint().
                accessTokenResponseClient(socialOAuth2AccessTokenResponseClient)
                .and().userInfoEndpoint().userService(socialOAuth2UserService);

        //.authorizationEndpoint().
        //                authorizationRequestResolver(this.SocialOAuth2AuthorizationRequestResolver)
        //                .and()
        //
//        http.formLogin()
//                //展示登录界面的URL
//                .loginPage("/authentication/require")
//                //配置登录处理的URL 在认证中心的登录界面 登录表单的提交地方
//                .loginProcessingUrl("/authentication/form")
//                .and()
//                .authorizeRequests()
//                .antMatchers("/authentication/require",
//                        "/authentication/form",
//                        "/**/*.js",
//                        "/**/*.css",
//                        "/**/*.jpg",
//                        "/**/*.png",
//                        "/**/*.woff2"
//                )
//                .permitAll()
//                .anyRequest()
//                .authenticated()
//                .and()
//                .csrf().disable();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }
}
