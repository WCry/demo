package me.format.springboot.log.autoconfigure;

import me.format.springboot.log.annotation.Log;
import me.format.springboot.log.aop.LogMethodInterceptor;
import org.aopalliance.aop.Advice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * 继承抽象切点的顾问
 */
@Configuration
@EnableConfigurationProperties(LogProperties.class)
public class LogAutoConfiguration extends AbstractPointcutAdvisor {

    private Logger logger = LoggerFactory.getLogger(LogAutoConfiguration.class);

    private Pointcut pointcut;

    private Advice advice;

    @Autowired
    private LogProperties logProperties;

    @PostConstruct
    public void init() {
        logger.info("init LogAutoConfiguration start");
        //通过注解配置切点  有Spring进行注解解释
        //面向切面也支持顺序
        this.pointcut = new AnnotationMatchingPointcut(null, Log.class);
        this.advice = new LogMethodInterceptor(logProperties.getExcludeArr());
        logger.info("init LogAutoConfiguration end");
    }

    /**
     * 获取切点
     * @return
     */
    @Override
    public Pointcut getPointcut() {
        return this.pointcut;
    }

    @Override
    public Advice getAdvice() {
        return this.advice;
    }

}
