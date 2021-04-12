1.
2.Spring注解详细参考Spring的官方说明
   https://docs.spring.io/spring/docs/5.3.0-SNAPSHOT/spring-framework-reference/
3.Spring的对象默认是单利模式实现，可以采用@Scope(value= ConfigurableBeanFactory.SCOPE_PROTOTYPE)
  实现原型模式（多实例模式）
4.在但实例对象中调用原型对象
   @Lookup
   public ScopeBInstance getClassB() {
          return null;
   }  
5.Spring几个常用注解
  @Autowired
  @Value
  @Resource
  @Qualifier
  @Bean
  @Controller
  @RestController
  @SpringApplication
  @Scope
  @RequestScope
  @SessionScope
  @ApplicationScope
  @Lookup
6.Spring解决循环依赖
  DefaultSingletonBeanRegistry类中采用三级缓存方式实现Bean的创建和解决循环依赖。
  singletonObjects：已经实力化好，初始化完成的对象
  earlySingletonObjects：当前简单的实力化好，为进行初始化对象（可以通过引用地址找到对象）
  singletonFactories：常见bean对象的工厂，负责实力化Bean
  
Springboot 高速上换轮胎开启远程调试部署：
https://blog.csdn.net/u010588262/article/details/86999228?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-1.channel_param&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-1.channel_param  
Spring 

7.Spring关于属性配置文件映射到类
@EnableConfigurationProperties @ConfigurationProperties @ConfigurationPropertiesScan
将属性配置到类中：https://blog.csdn.net/u013202238/article/details/107133200
属性配置文件书写时候能够自动进行提示
https://www.cnblogs.com/jimoer/p/11374229.html

8.Spring将IOC管理类和new新建类的联合编辑，联合使用
https://www.ripjava.com/article/1300532754907168

9.Spring的配置Bean的类proxyBeanMethods在配置类中是否代理其中产生Bean的方法
如果代理完全有IOC的方式管理，如果false，调用配置类的方法，每一次都会产生一个新的类
https://www.ripjava.com/article/1300532754907168

10.Spring自定义加载外在扩展配置ImportSelector还有ImportBeanDefinitionRegistrar等
主要查看ConfigurationClassParser类中处理@Import的过程processImports中定义的类
https://blog.csdn.net/elim168/article/details/88131614

11.SpringBoot利用@Import导入实现@EnableXXX的自定义加载需要的配置
ConfigurationClassParser类中处理@Import的过程processImports

12.JMX(Java Management Extensions，即Java管理扩展)@EnableMBeanExport

Spring了解
https://elim168.blog.csdn.net/category_9269225_2.html