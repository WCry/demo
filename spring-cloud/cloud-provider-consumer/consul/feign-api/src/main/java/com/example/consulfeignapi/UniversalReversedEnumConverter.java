package com.example.consulfeignapi;

/**
 * @author zhangxuepei
 * @since 3.0
 */
import org.springframework.core.convert.converter.Converter;

public class UniversalReversedEnumConverter implements Converter<NamedEnum, String> {

    @Override
    public String convert(NamedEnum source) {
        return source.getName();
    }
}