/**
 * aop处理
 * mvn依赖
 * <dependency>
 * <groupId>org.springframework.boot</groupId>
 * <artifactId>spring-boot-starter-aop</artifactId>
 * </dependency>
 * 基于Springboot2.X
 * Aspect的AOP
 * 可以使用三个注解是其生效
 * @Aspect
 * @Order(1)
 * @Component
 *
 * Aspect配置 能够自动代理
 * @EnableAspectJAutoProxy
 * proxy-target-class属性值决定是接口的还是类的代理被创建。如果proxy-target-class 属性值被设置为true，那么类的代理将起作用，
 * 如cglib库；如果proxy-target-class属值被设置为false或者这个属性被省略，那么标准的JDK基于接口的代理。
 * 当然，如果没有对应接口，只有实现，类的代理将起作用。
 * exposeProxy：为是否暴露当前代理对象为ThreadLocal模式。那如何在同一个对象里让事务传播行为生效，true
 * order的执行顺序是，before 1-2-3
 * after 3-2-1
 * 关于Aspect的切点定义规则具体参考Aspect定义
 *
 * 需要注意SpringBoot中其他依赖AOP的实现的相互影响
 *
 *  //需要注意
 * ((UserService)AopContext.currentProxy()).addSalary(account,salary);
 */
package demo.aop;