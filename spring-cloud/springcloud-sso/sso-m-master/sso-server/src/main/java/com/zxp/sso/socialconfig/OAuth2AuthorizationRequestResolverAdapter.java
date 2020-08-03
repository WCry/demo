package com.zxp.sso.socialconfig;

/**
 * user:zxp
 * Day:2020,08,02
 **/
public interface OAuth2AuthorizationRequestResolverAdapter {
    Boolean canProcess(String registrationId);

}
