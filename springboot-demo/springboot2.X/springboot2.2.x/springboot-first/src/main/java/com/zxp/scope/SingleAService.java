package com.zxp.scope;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Service;

/**
 * @author zhangxuepei
 * @since 3.0
 */
@Slf4j
@Service
public class SingleAService {

    public void printClass() {
        log.debug("This is Class A: " + this);
        log.debug(getClassB().getName());
    }

    public String getName() {
        return getClassB().getName();
    }

    public void setName(String name) {
        ScopeBInstance scopeBInstance = getClassB();
        scopeBInstance.setName(name);
        log.debug(scopeBInstance.getName());
    }

    /**
     * SpringBoot 默认实现Bean是单利模式
     * 需要在类上增加@Scope(value= ConfigurableBeanFactory.SCOPE_PROTOTYPE)注解
     * 同时胚子@Lookup注解方法获取原型类
     * 这里返回null 看着是很匪夷所思。但是@Lookup注解生成一个子类
     * 可以和C服务中的对象进行对比
     * @return
     */
    @Lookup
    public ScopeBInstance getClassB() {
        return null;
    }
}
