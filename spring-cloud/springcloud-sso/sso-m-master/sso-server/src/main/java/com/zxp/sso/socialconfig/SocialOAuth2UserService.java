package com.zxp.sso.socialconfig;

import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * user:zxp
 * Day:2020,07,27
 * 定义获取用户信息
 **/
@Component
public class SocialOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    @Resource
    private RestTemplate restTemplate;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
        ClientRegistration clientRegistration = oAuth2UserRequest.getClientRegistration();
        String registrationId = clientRegistration.getRegistrationId();
        Map<String, Object> additionalParameters = oAuth2UserRequest.getAdditionalParameters();
        String uid = additionalParameters.get("uid").toString();
        String access_token = oAuth2UserRequest.getAccessToken().getTokenValue();
        Map<String, String> params = new HashMap<>();
        params.put("uid", uid);
        params.put("access_token", access_token);
        String baseUri = oAuth2UserRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUri();
        String userInfoUri = baseUri + "?uid={uid}" + "&access_token={access_token}";
        System.out.println(userInfoUri);
        return restTemplate.getForObject(userInfoUri, WeiboOAuth2User.class, params);
    }
}
