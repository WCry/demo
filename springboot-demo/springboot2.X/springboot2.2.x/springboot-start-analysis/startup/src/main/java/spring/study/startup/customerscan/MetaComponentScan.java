package spring.study.startup.customerscan;

import org.springframework.context.annotation.AutoProxyRegistrar;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zhangxuepei
 * @since 3.0
 * 自定义实现Bean注解和扫描
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({MetaAutoConfigureRegistrar.class})
public @interface MetaComponentScan {
    @AliasFor("basePackages") String[] value() default {};
    @AliasFor("value") String[] basePackages() default {};
    Class<?>[] basePackageClasses() default{};
}
