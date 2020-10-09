package com.zxp.service;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 开启属性文件的映射到实体对象
 */
@ConfigurationProperties(prefix = "zxp.hello")
public class HelloProperties {

    private String prefix;
    private String suffix;

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

}
