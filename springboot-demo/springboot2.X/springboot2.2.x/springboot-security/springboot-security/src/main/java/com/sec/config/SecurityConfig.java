package com.sec.config;


import com.sec.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.util.DigestUtils;

/**
 * 安全配置适配类
 *  处理一些安全适配
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final MyUserDetailsService userService;
    @Autowired
    public SecurityConfig(MyUserDetailsService userService) {
        this.userService = userService;
    }

    /**
     * 配置密码 解析器
     * @param auth  身份验证管理器
     * @throws Exception
     * autowired 注解在方法上，Springboot会在初始化方法的时候，注入方法需要的参数，并且执行一遍
     *  类似与静态代码块static{}
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        //配置自定义权限验证服务同时设置密码加密解密方式
        auth.userDetailsService(userService).passwordEncoder( new PasswordEncoder() {
            //对密码进行加密
            @Override
            public String encode(CharSequence charSequence) {
                System.out.println(charSequence.toString());
                return DigestUtils.md5DigestAsHex(charSequence.toString().getBytes());
            }
            //对密码进行判断匹配
            @Override
            public boolean matches(CharSequence charSequence, String s) {
                String encode = DigestUtils.md5DigestAsHex(charSequence.toString().getBytes());
                boolean res = s.equals( encode );
                return res;
            }
        } );

    }

    /**
     * 配置网站权限 方放行规则
     * @param http http安全
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //配置http的授权 匹配到当前请求 放行
        //设置 至对于身份进行验证
        http.authorizeRequests()
                .antMatchers("/","index","/login","/login-error","/401","/css/**","/js/**").permitAll()
                .anyRequest().authenticated()
                //增加 and
                .and()
                //登录界面  登录失败之后的url
                .formLogin().loginPage( "/login" ).failureUrl( "/login-error" )
                .and()
                //配置url异常处理 全选拒绝页面
                .exceptionHandling().accessDeniedPage( "/401" ).and();
        //每一个and 请求是一个设置的完成
        http.logout().logoutSuccessUrl( "/" );
       // http.addFilterBefore(new BeforeLoginFilter(), ChannelProcessingFilter.class);
    }
}
