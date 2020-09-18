package com.format.springboot.log.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.concurrent.CountDownLatch;


@ConfigurationProperties(prefix = "mylog")
public class LogProperties {

    private String exclude;

    private String[] excludeArr;

    @PostConstruct
    public void init() {
        this.excludeArr = StringUtils.split(exclude, ",");
    }

    public String getExclude() {
        return exclude;
    }

    public void setExclude(String exclude) {
        this.exclude = exclude;
    }

    public String[] getExcludeArr() {
        return excludeArr;
    }
}
