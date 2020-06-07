package com.zxp.soo.client.login.mobile;

/**
 * user:zxp
 * Day:2020,06,05
 **/
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.thymeleaf.util.StringUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * 用户名密码
 */
public class UsernamePasswordAuthenticationProvider implements AuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = (authentication.getPrincipal() == null) ? "NONE_PROVIDED" : authentication.getName();
        String password = (String) authentication.getCredentials();
        // 认证用户名
        if (!"user".equals(username) && !"admin".equals(username)) {
            throw new BadCredentialsException("用户不存在");
        }
        // 认证密码，暂时不加密
        if ("user".equals(username) && !"123".equals(password) || "admin".equals(username) && !"admin".equals(password)) {
            throw new BadCredentialsException("密码不正确");
        }
        UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(username,
                authentication.getCredentials(), listUserGrantedAuthorities(username));
        result.setDetails(authentication.getDetails());
        return result;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }

    private Set<GrantedAuthority> listUserGrantedAuthorities(String username) {
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        if(StringUtils.isEmpty(username)) {
            return authorities;
        }
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        if ("admin".equals(username)) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        return authorities;
    }
}
