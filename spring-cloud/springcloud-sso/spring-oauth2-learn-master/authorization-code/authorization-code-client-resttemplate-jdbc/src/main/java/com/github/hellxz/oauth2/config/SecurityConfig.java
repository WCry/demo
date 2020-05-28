package com.github.hellxz.oauth2.config;

import com.github.hellxz.oauth2.dao.ClientUserRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
   @Autowired
    public SecurityConfig(ClientUserRepositories clientUserRepositories, ClientUserDetailsService clientUserDetailsService, DataSource dataSource) {
        this.clientUserRepositories = clientUserRepositories;
        this.clientUserDetailsService = clientUserDetailsService;
        this.dataSource = dataSource;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    private final ClientUserRepositories clientUserRepositories;

    private final ClientUserDetailsService clientUserDetailsService;

    private final DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //@formatter:off
        http.authorizeRequests()
                //仅放通登录接口
                .antMatchers(HttpMethod.GET, "/", "/index", "/api/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic();
        //@formatter:on
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(clientUserDetailsService);
    }
}
