package annotationtest;

import java.lang.annotation.*;

/**
 * 类 名: MyService
 * 描 述: 自定义注解 -- 服务层 -- 定义在类、接口、枚举上的
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@MyComponent
public @interface MyService {

}
