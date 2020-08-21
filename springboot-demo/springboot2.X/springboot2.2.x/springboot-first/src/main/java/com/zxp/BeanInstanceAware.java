package com.zxp;

import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;

/**
 * @author zhangxuepei
 * @since 3.0
 * 实现Bean对象的初始化，感知，初始化适配
 * 适配器中进行空的实现，自己根据需要实现自己需要方法
 */
public class BeanInstanceAware extends InstantiationAwareBeanPostProcessorAdapter {}
