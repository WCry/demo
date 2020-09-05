1.Spring注解详细参考Spring的官方说明
   https://docs.spring.io/spring/docs/5.3.0-SNAPSHOT/spring-framework-reference/
2.Spring的对象默认是单利模式实现，可以采用@Scope(value= ConfigurableBeanFactory.SCOPE_PROTOTYPE)
  实现原型模式（多实例模式）
3.在但实例对象中调用原型对象
   @Lookup
   public ScopeBInstance getClassB() {
          return null;
   }  
4.Spring几个常用注解
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
5.Spring解决循环依赖
  DefaultSingletonBeanRegistry类中采用三级缓存方式实现Bean的创建和解决循环依赖。
  singletonObjects：已经实力化好，初始化完成的对象
  earlySingletonObjects：当前简单的实力化好，为进行初始化对象（可以通过引用地址找到对象）
  singletonFactories：常见bean对象的工厂，负责实力化Bean
  
Springboot 高速上换轮胎开启远程调试部署：
https://blog.csdn.net/u010588262/article/details/86999228?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-1.channel_param&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-1.channel_param  
Spring 