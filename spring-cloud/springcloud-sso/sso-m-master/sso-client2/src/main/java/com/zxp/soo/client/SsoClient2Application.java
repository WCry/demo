package com.zxp.soo.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
@EnableOAuth2Sso   //该注解是在Sso的客户端使用

//@EnableOAuth2Client  这个是是 单独使用Oath2的时候使用 在sso模式不使用
public class SsoClient2Application {

    /**
     *  该接口测试将 认证信息 传递到接口当中 共接口使用
     * @param user
     * @return
     */
    @GetMapping("/user")
    public Authentication user(Authentication user) {
        //Authentication 是Springboot认证的信息接口的基类
        //获取OAuth2的身份认证  这个是根据具体认证  转换成具体类
        OAuth2Authentication oAuth2Authentication=(OAuth2Authentication)user;
        System.out.println(oAuth2Authentication.getOAuth2Request().getScope());
        return user;
    }
    public static void main(String[] args) {
        SpringApplication.run(SsoClient2Application.class, args);
    }
}
