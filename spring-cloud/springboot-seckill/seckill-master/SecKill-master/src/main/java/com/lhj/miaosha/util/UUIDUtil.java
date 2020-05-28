package com.lhj.miaosha.util;

import java.util.UUID;

/**
 * @author ：LHJ
 * @date ：2019/7/26 15:34
 * @description：生成uuid用于唯一标识用户session
 */
public class UUIDUtil {
    public static String uuid(){
        return UUID.randomUUID().toString().replace("-","");

    }
}
