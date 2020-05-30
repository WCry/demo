package com.lingchaomin.auth.cas.plugin.wechat;

import org.pac4j.core.profile.converter.Converters;
import org.pac4j.oauth.profile.OAuthAttributesDefinition;

/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/2/13 下午2:32
 * @description 微信返回属性值
 */
public class WechatAttributeDefinition extends OAuthAttributesDefinition {

    public static final String OPEN_ID = "openid";
    public static final String NICK_NAME = "nickname";
    /** 用户性别,1为男性,2为女性 */
    public static final String SEX = "sex";
    public static final String COUNTRY = "country";
    public static final String PROVINCE = "province";
    public static final String CITY = "city";
    public static final String HEAD_IMG_URL = "headimgurl";
    public static final String PRIVILEGE = "privilege";
    public static final String UNION_ID = "unionid";
    // appended
    public static final String APP_NAME = "appName";
    public static final String SUID = "suid";

    public WechatAttributeDefinition(){
        addAttribute(OPEN_ID, Converters.stringConverter);
        addAttribute(NICK_NAME, Converters.stringConverter);
        addAttribute(SEX, Converters.integerConverter);
        addAttribute(COUNTRY, Converters.stringConverter);
        addAttribute(PROVINCE, Converters.stringConverter);
        addAttribute(CITY, Converters.stringConverter);
        addAttribute(HEAD_IMG_URL, Converters.stringConverter);
        addAttribute(UNION_ID, Converters.stringConverter);
        addAttribute(APP_NAME, Converters.stringConverter);
        addAttribute(SUID, Converters.longConverter);
    }

}
