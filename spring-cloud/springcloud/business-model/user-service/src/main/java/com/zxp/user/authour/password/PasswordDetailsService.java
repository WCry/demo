package com.zxp.user.authour.password;

import com.zxp.user.params.UserSecurity;
import com.zxp.user.params.query.UserAccountQuery;
import com.zxp.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * 用户详细信息服务
 */
@Component
public class PasswordDetailsService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    @Autowired
    public PasswordDetailsService(PasswordEncoder passwordEncoder, UserService userService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAccountQuery userAccountQuery=new UserAccountQuery(username);
        Optional<UserSecurity> userSecurity= userService.findUserSecurityDTOByUserAccount(userAccountQuery);
        return new User(username, passwordEncoder.encode(userSecurity.get().getPassword()),
                AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER,ROLE_LOW,ROLE_MID"));
    }
}
