package com.zxp.user.authour.password;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;

@Configuration
@Order(SecurityProperties.DEFAULT_FILTER_ORDER)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final LoginSuccessHandler loginSuccessHandler;
    private final JdbcTokenRepositoryImpl jdbcTokenRepository;
    @Autowired
    public SecurityConfiguration(LoginSuccessHandler loginSuccessHandler,JdbcTokenRepositoryImpl jdbcTokenRepository) {
        this.loginSuccessHandler = loginSuccessHandler;
        this.jdbcTokenRepository = jdbcTokenRepository;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        //  auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
        //remember me
        auth.eraseCredentials(false);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().loginPage("/authentication/require").permitAll().
                successHandler(loginSuccessHandler).and().authorizeRequests()
                .antMatchers("/images/**", "/checkCode", "/scripts/**", "/styles/**")
                .permitAll().anyRequest().authenticated()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER)
                .and().exceptionHandling().accessDeniedPage("/deny")
                .and().rememberMe().tokenValiditySeconds(86400).tokenRepository(jdbcTokenRepository);
    }
}
