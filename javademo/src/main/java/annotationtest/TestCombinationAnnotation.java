package annotationtest;

import javax.annotation.*;
import java.lang.annotation.*;

/**
 * 采用递归方式实现注解的合并
 */
public class TestCombinationAnnotation {
    public static void main(String[] args) {
        Class<UserService> classZ = UserService.class;
        getAnnotations(classZ);
    }

    /**
     * interface java.lang.annotation.Documented 等 存在循环，导致内存溢出，所以需要排除java的源注解
     * @param classZ
     */
    private static void getAnnotations(Class<?> classZ){
        Annotation[] annotations = classZ.getAnnotations();
        for (Annotation annotation : annotations) {
            if (annotation.annotationType() != Deprecated.class &&
                    annotation.annotationType() != SuppressWarnings.class &&
                    annotation.annotationType() != Override.class &&
                    annotation.annotationType() != PostConstruct.class &&
                    annotation.annotationType() != PreDestroy.class &&
                    annotation.annotationType() != Resource.class &&
                    annotation.annotationType() != Resources.class &&
                    annotation.annotationType() != Generated.class &&
                    annotation.annotationType() != Target.class &&
                    annotation.annotationType() != Retention.class &&
                    annotation.annotationType() != Documented.class &&
                    annotation.annotationType() != Inherited.class
            ) {
                if (annotation.annotationType() == MyComponent.class){
                    System.out.println(" 存在注解 @MyComponent ");
                }else{
                    getAnnotations(annotation.annotationType());
                }
            }
        }
    }
}
