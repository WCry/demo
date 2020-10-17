package com.zxp.validator;



import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ValidatorUtil {

    //默认以1开头后面加10个数字为手机号
    private static final Pattern mobile_pattern = Pattern.compile("1\\d{10}");

    public static boolean isMobile(String src){
        if(StringUtils.isEmpty(src)){
            return false;
        }
        Matcher m = mobile_pattern.matcher(src);
        return m.matches();
    }
}
