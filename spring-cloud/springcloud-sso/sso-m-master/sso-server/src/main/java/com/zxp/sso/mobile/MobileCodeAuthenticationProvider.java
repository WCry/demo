//package com.zxp.sso.mobile;
//
///**
// * user:zxp
// * Day:2020,06,05
// **/
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.thymeleaf.util.StringUtils;
//
//import java.util.HashSet;
//import java.util.Set;
//
///**
// * 手机验证
// */
//public class MobileCodeAuthenticationProvider implements AuthenticationProvider {
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        String mobile = (authentication.getPrincipal() == null) ? "NONE_PROVIDED" : authentication.getName();
//        String code = (String) authentication.getCredentials();
//        if (StringUtils.isEmpty(code)) {
//            throw new BadCredentialsException("验证码不能为空");
//        }
//        if (!"13999990000".equals(mobile)) {
//            throw new BadCredentialsException("用户不存在");
//        }
//        // 手机号验证码业务还没有开发，先用4个0验证
//        if (!code.equals("0000")) {
//            throw new BadCredentialsException("验证码不正确");
//        }
//        MobileCodeAuthenticationToken result = new MobileCodeAuthenticationToken(mobile, listUserGrantedAuthorities(mobile));
//        result.setDetails(authentication.getDetails());
//        return result;
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return (MobileCodeAuthenticationToken.class.isAssignableFrom(authentication));
//    }
//
//    private Set<GrantedAuthority> listUserGrantedAuthorities(String username) {
//        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
//        if (StringUtils.isEmpty(username)) {
//            return authorities;
//        }
//        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
//        return authorities;
//    }
//
//}
