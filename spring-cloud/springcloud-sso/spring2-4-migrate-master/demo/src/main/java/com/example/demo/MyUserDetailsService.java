package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * user:zxp
 * Day:2020,03,29
 **/
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        //查数据库
        User user = userMapper.loadUserByUsername( userName );
        if (null != user) {
            List<Role> roles = roleMapper.getRolesByUserId( user.getId() );
            user.setAuthorities( roles );
        }

        return user;
    }
}
