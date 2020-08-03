package com.zxp.sso.mobile;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;

/**
 * user:zxp
 * Day:2020,07,31
 **/

@Component
public class PhoneCodeAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        PhoneCodeAuthenticationToken authenticationToken=(PhoneCodeAuthenticationToken)authentication;

        String phone=authenticationToken.getPrincipal().toString();
        System.out.println(phone);

        checkMessageCode(phone);

        System.out.println("认证成功");
        PhoneCodeAuthenticationToken authenticationResult=new PhoneCodeAuthenticationToken(phone, Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
        authenticationResult.setDetails(authenticationToken.getDetails());

        return authenticationResult;
    }

    private void checkMessageCode(String phone){
        HttpServletRequest request=((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        String request_code=request.getParameter("code");

        Map<String,String> map=(Map<String, String>) request.getSession().getAttribute("message_code");
        if (map==null){
            throw new BadCredentialsException("未申请验证码");
        }

        String session_phone= map.get("phone");
        String session_code= map.get("code");

        if (!phone.equals(session_phone)){
            throw new BadCredentialsException("申请验证的手机号不一致");
        }

        if (!request_code.equals(session_code)){
            throw new BadCredentialsException("验证码不一致");
        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return PhoneCodeAuthenticationToken.class.isAssignableFrom(aClass);
    }
}