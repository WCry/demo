package com.zxp.sso.mobile;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * user:zxp
 * Day:2020,07,31
 **/
public class PhoneCodeAuthenticationToken extends AbstractAuthenticationToken {

    private static final long serialVersionUID = 500L;

    private final Object principal;

    public PhoneCodeAuthenticationToken(Object principal) {
        super(null);
        this.principal = principal;
        this.setAuthenticated(false);
    }

    public PhoneCodeAuthenticationToken(Object principal, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        super.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    public Object getPrincipal() {
        return this.principal;
    }

    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        if (isAuthenticated) {
            throw new IllegalArgumentException("Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        } else {
            super.setAuthenticated(false);
        }
    }

    public void eraseCredentials() {
        super.eraseCredentials();
    }
}
