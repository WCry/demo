package com.example.consulfeignapi;

/**
 * @author zhangxuepei
 * @since 3.0
 */
public enum Language implements NamedEnum {

    UNLIMITED("--"),

    // 英语
    ENGLISH("en"),

    // 简体中文
    CHINESE_SIMPLIFIED("zh-CN"),

    // 繁体中文
    CHINESE_TRADITIONAL("zh-TW");

    //语言的ISO_639-1缩写
    private String name;

    public String getName() {
        return name;
    }

    Language(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
