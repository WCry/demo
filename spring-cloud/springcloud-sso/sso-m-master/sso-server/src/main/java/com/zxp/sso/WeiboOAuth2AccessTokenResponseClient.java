package com.zxp.sso;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.endpoint.OAuth2AccessTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequest;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.endpoint.OAuth2AccessTokenResponse;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationExchange;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * user:zxp
 * Day:2020,07,27
 **/
@Component
public class WeiboOAuth2AccessTokenResponseClient implements OAuth2AccessTokenResponseClient<OAuth2AuthorizationCodeGrantRequest> {

    private final ObjectMapper objectMapper;
    @Resource
    private RestTemplate restTemplate;
    @Autowired
    public WeiboOAuth2AccessTokenResponseClient(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public OAuth2AccessTokenResponse getTokenResponse(OAuth2AuthorizationCodeGrantRequest oAuth2AuthorizationCodeGrantRequest) {
        ClientRegistration clientRegistration=oAuth2AuthorizationCodeGrantRequest.getClientRegistration();
        System.out.println("registerId："+clientRegistration.getRegistrationId());

        OAuth2AuthorizationExchange oAuth2AuthorizationExchange=oAuth2AuthorizationCodeGrantRequest.getAuthorizationExchange();

        Map<String,String> params=new HashMap<>();
        params.put("client_id",clientRegistration.getClientId());
        params.put("client_secret",clientRegistration.getClientSecret());
        params.put("grant_type",clientRegistration.getAuthorizationGrantType().getValue());
        params.put("code",oAuth2AuthorizationExchange.getAuthorizationResponse().getCode());
        params.put("redirect_uri",oAuth2AuthorizationExchange.getAuthorizationResponse().getRedirectUri());
        System.out.println(params);

        String baseUri=clientRegistration.getProviderDetails().getTokenUri();

        String accessTokenUri=baseUri+ "?client_id={client_id}"+
                "&client_secret={client_secret}"+
                "&grant_type={grant_type}"+
                "&redirect_uri={redirect_uri}"+
                "&code={code}";

        String accessTokenResponse=restTemplate.postForObject(accessTokenUri,null,String.class,params);

        JsonNode object= null;
        try {
            object = objectMapper.readTree(accessTokenResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String accessToken=object.get("access_token").asText();
        String expires_in=object.get("expires_in").asText();
        String uid=object.get("uid").asText();

        Map<String,Object> additionalParameters=new HashMap<>();
        additionalParameters.put("uid",uid);

        return OAuth2AccessTokenResponse.withToken(accessToken)
                .expiresIn(Long.parseLong(expires_in))
                .tokenType(OAuth2AccessToken.TokenType.BEARER)
                .scopes(oAuth2AuthorizationExchange.getAuthorizationRequest().getScopes())
                .additionalParameters(additionalParameters)
                .build();
    }
}
