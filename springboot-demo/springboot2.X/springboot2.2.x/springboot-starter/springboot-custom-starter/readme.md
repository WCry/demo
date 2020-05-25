#### 实现Starter制作：

   1. 实现自定义的Springboot的配置类，实现自己的业务

   2.  实现加载类

      通过配置文件，自动注入启动

       在resources/META-INF/spring.properties 配置自动注册声明，类似于这样：

      ```properties
      org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
      com.zxp.service.HelloServiceAutoConfiguration
      ```

      通过注解实现开关启动：

      ```java
      @Target(ElementType.TYPE)
      @Retention(RetentionPolicy.RUNTIME)
      @Documented
      @Import(HelloServiceAutoConfiguration.class)
      public @interface EnableHelloConfig {}
      ```

​      3.在使用类中，添加Starter类库的依赖。可以自己的starter类库了。




