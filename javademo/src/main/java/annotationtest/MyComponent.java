package annotationtest;

import java.lang.annotation.*;

/**
 * 基础注解，被此注解标记的类在spring扫描的包路径下的话会被加入候选资源中
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyComponent {

    String name() default "";
}
