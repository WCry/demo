package com.zxp.demo.flink.util;

import org.apache.flink.shaded.netty4.io.netty.util.internal.SystemPropertyUtil;

/**
 * @author zhangxuepei
 * @since 3.0
 */
public class EnvironmentUtil {
    public static String startDir= SystemPropertyUtil.get("user.dir");

}
