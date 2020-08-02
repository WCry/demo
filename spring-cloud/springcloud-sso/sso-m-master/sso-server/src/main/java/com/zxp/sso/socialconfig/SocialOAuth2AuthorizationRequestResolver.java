package com.zxp.sso.socialconfig;

import org.springframework.security.crypto.keygen.Base64StringKeyGenerator;
import org.springframework.security.crypto.keygen.StringKeyGenerator;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestRedirectFilter;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * user:zxp
 * Day:2020,08,02
 * 默认的认证请求处理
 * DefaultOAuth2AuthorizationRequestResolver
 * 认证请求服务处理者，进行适配weibo,微信，qq，支付宝的认证请求处理
 * 更新认证ID 判断URL 进行处理
 **/
@Component
public class SocialOAuth2AuthorizationRequestResolver implements OAuth2AuthorizationRequestResolver {
    private static final String authorizationRequestBaseUri =OAuth2AuthorizationRequestRedirectFilter.DEFAULT_AUTHORIZATION_REQUEST_BASE_URI;
    private static final String REGISTRATION_ID_URI_VARIABLE_NAME = "registrationId";
    private static final char PATH_DELIMITER = '/';
    private final ClientRegistrationRepository clientRegistrationRepository;
    private final AntPathRequestMatcher authorizationRequestMatcher;
    private final StringKeyGenerator stateGenerator = new Base64StringKeyGenerator(Base64.getUrlEncoder());
    private final StringKeyGenerator secureKeyGenerator = new Base64StringKeyGenerator(Base64.getUrlEncoder().withoutPadding(), 96);

    public SocialOAuth2AuthorizationRequestResolver(ClientRegistrationRepository clientRegistrationRepository) {
        Assert.notNull(clientRegistrationRepository, "clientRegistrationRepository cannot be null");
        Assert.hasText(authorizationRequestBaseUri, "authorizationRequestBaseUri cannot be empty");
        this.clientRegistrationRepository = clientRegistrationRepository;
        this.authorizationRequestMatcher = new AntPathRequestMatcher(authorizationRequestBaseUri + "/{" + "registrationId" + "}");
    }

    public OAuth2AuthorizationRequest resolve(HttpServletRequest request) {
        String registrationId = this.resolveRegistrationId(request);
        //获取重定向URL处理
        String redirectUriAction = this.getAction(request, "login");

        return this.resolve(request, registrationId, redirectUriAction);
    }

    public OAuth2AuthorizationRequest resolve(HttpServletRequest request, String registrationId) {
        if (registrationId == null) {
            return null;
        } else {
            //
            String redirectUriAction = this.getAction(request, "authorize");
            return this.resolve(request, registrationId, redirectUriAction);
        }
    }

    private String getAction(HttpServletRequest request, String defaultAction) {
        String action = request.getParameter("action");
        //获取action
        return action == null ? defaultAction : action;
    }

    private OAuth2AuthorizationRequest resolve(HttpServletRequest request, String registrationId, String redirectUriAction) {
        if (registrationId == null) {
            return null;
        } else {
            ClientRegistration clientRegistration = this.clientRegistrationRepository.findByRegistrationId(registrationId);
            if (clientRegistration == null) {
                throw new IllegalArgumentException("Invalid Client Registration with Id: " + registrationId);
            } else {
                Map<String, Object> attributes = new HashMap();
                attributes.put("registration_id", clientRegistration.getRegistrationId());
                OAuth2AuthorizationRequest.Builder builder;
                if (AuthorizationGrantType.AUTHORIZATION_CODE.equals(clientRegistration.getAuthorizationGrantType())) {
                    builder = OAuth2AuthorizationRequest.authorizationCode();
                    Map<String, Object> additionalParameters = new HashMap();
                    if (!CollectionUtils.isEmpty(clientRegistration.getScopes()) && clientRegistration.getScopes().contains("openid")) {
                        this.addNonceParameters(attributes, additionalParameters);
                    }

                    if (ClientAuthenticationMethod.NONE.equals(clientRegistration.getClientAuthenticationMethod())) {
                        this.addPkceParameters(attributes, additionalParameters);
                    }

                    builder.additionalParameters(additionalParameters);
                } else {
                    if (!AuthorizationGrantType.IMPLICIT.equals(clientRegistration.getAuthorizationGrantType())) {
                        throw new IllegalArgumentException("Invalid Authorization Grant Type (" + clientRegistration.getAuthorizationGrantType().getValue() + ") for Client Registration with Id: " + clientRegistration.getRegistrationId());
                    }

                    builder = OAuth2AuthorizationRequest.implicit();
                }

                String redirectUriStr = expandRedirectUri(request, clientRegistration, redirectUriAction);
                OAuth2AuthorizationRequest authorizationRequest = builder.clientId(clientRegistration.getClientId()).
                        authorizationUri(clientRegistration.getProviderDetails().getAuthorizationUri()).
                        redirectUri(redirectUriStr).scopes(clientRegistration.getScopes()).
                        state(this.stateGenerator.generateKey()).attributes(attributes).build();
                return authorizationRequest;
            }
        }
    }

    private String resolveRegistrationId(HttpServletRequest request) {
        return this.authorizationRequestMatcher.matches(request) ? (String)this.authorizationRequestMatcher.matcher(request).getVariables().get("registrationId") : null;
    }

    private static String expandRedirectUri(HttpServletRequest request, ClientRegistration clientRegistration, String action) {
        Map<String, String> uriVariables = new HashMap();
        uriVariables.put("registrationId", clientRegistration.getRegistrationId());
        UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(UrlUtils.buildFullRequestUrl(request)).replacePath(request.getContextPath()).replaceQuery((String)null).fragment((String)null).build();
        String scheme = uriComponents.getScheme();
        uriVariables.put("baseScheme", scheme == null ? "" : scheme);
        String host = uriComponents.getHost();
        uriVariables.put("baseHost", host == null ? "" : host);
        int port = uriComponents.getPort();
        uriVariables.put("basePort", port == -1 ? "" : ":" + port);
        String path = uriComponents.getPath();
        if (StringUtils.hasLength(path) && path.charAt(0) != '/') {
            path = '/' + path;
        }

        uriVariables.put("basePath", path == null ? "" : path);
        uriVariables.put("baseUrl", uriComponents.toUriString());
        uriVariables.put("action", action == null ? "" : action);
        return UriComponentsBuilder.fromUriString(clientRegistration.getRedirectUriTemplate()).buildAndExpand(uriVariables).toUriString();
    }

    private void addNonceParameters(Map<String, Object> attributes, Map<String, Object> additionalParameters) {
        try {
            String nonce = this.secureKeyGenerator.generateKey();
            String nonceHash = createHash(nonce);
            attributes.put("nonce", nonce);
            additionalParameters.put("nonce", nonceHash);
        } catch (NoSuchAlgorithmException var5) {
        }

    }

    private void addPkceParameters(Map<String, Object> attributes, Map<String, Object> additionalParameters) {
        String codeVerifier = this.secureKeyGenerator.generateKey();
        attributes.put("code_verifier", codeVerifier);

        try {
            String codeChallenge = createHash(codeVerifier);
            additionalParameters.put("code_challenge", codeChallenge);
            additionalParameters.put("code_challenge_method", "S256");
        } catch (NoSuchAlgorithmException var5) {
            additionalParameters.put("code_challenge", codeVerifier);
        }

    }

    private static String createHash(String value) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] digest = md.digest(value.getBytes(StandardCharsets.US_ASCII));
        return Base64.getUrlEncoder().withoutPadding().encodeToString(digest);
    }
}
