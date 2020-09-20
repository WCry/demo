package com.zxp.user.authour.password;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;


@Configuration
public class SsoSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private  final LoginSuccessHandler loginSuccessHandler;
    private final JdbcTokenRepositoryImpl jdbcTokenRepository;
    @Autowired
    public SsoSecurityConfig(@Qualifier("passwordDetailsService") UserDetailsService userDetailsService, LoginSuccessHandler loginSuccessHandler, JdbcTokenRepositoryImpl jdbcTokenRepository)
    {
        this.userDetailsService = userDetailsService;
        this.loginSuccessHandler = loginSuccessHandler;
        this.jdbcTokenRepository = jdbcTokenRepository;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.formLogin().loginPage("/authentication/require").and().authorizeRequests()
//                .antMatchers("/message").permitAll().and().authorizeRequests()
//                .antMatchers("/hello").hasAuthority("ROLE_USER");
        http.formLogin().loginPage("/authentication/require").permitAll().
                successHandler(loginSuccessHandler).and().authorizeRequests()
                .antMatchers("/images/**", "/checkCode", "/scripts/**", "/styles/**")
                .permitAll().anyRequest().authenticated()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER)
                .and().exceptionHandling().accessDeniedPage("/deny")
                .and().rememberMe().tokenValiditySeconds(86400).tokenRepository(jdbcTokenRepository);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }
}
