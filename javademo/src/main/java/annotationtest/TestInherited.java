package annotationtest;

import java.lang.annotation.Annotation;
import java.util.Arrays;

/**
 * @Inherited是一个标识，用来修饰注解
 * 作用：如果一个类用上了@Inherited修饰的注解，那么其子类也会继承这个注解
 *
 * 注意：
 *
 * 接口用上个@Inherited修饰的注解，其实现类不会继承这个注解
 * 父类的方法用了@Inherited修饰的注解，子类也不会继承这个注解
 * 当用了@Inherited修饰的注解的@Retention是RetentionPolicy.RUNTIME，则增强了继承性，在反射中可以获取得到
 */
public class TestInherited {
    public static void main(String[] args) {
        Class<Sub> clazz = Sub.class;
        System.out.println("============================AnnotatedElement===========================");
        System.out.println(Arrays.toString(clazz.getAnnotations()));    //获取自身和父亲的注解
        System.out.println("------------------");
        System.out.println(Arrays.toString(clazz.getDeclaredAnnotations()));  //只获取自身的注解
        System.out.println("------------------");
        System.out.println("============================Field===========================");
        System.out.println(Arrays.toString(clazz.getFields())); // 自身和父亲的公有字段
        System.out.println("------------------");
        System.out.println(Arrays.toString(clazz.getDeclaredFields()));  //自身所有字段
        System.out.println("============================Method===========================");
        System.out.println(Arrays.toString(clazz.getMethods()));   //自身和父亲的公有方法
        System.out.println("------------------");
        System.out.println(Arrays.toString(clazz.getDeclaredMethods()));// 自身所有方法
        System.out.println("============================Constructor===========================");
        System.out.println(Arrays.toString(clazz.getConstructors()));   //自身公有的构造方法
        System.out.println("------------------");
        System.out.println(Arrays.toString(clazz.getDeclaredConstructors()));   //自身的所有构造方法
    }
}
