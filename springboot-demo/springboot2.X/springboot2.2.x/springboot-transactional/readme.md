SpringBoot 事务注意事项：

  

```
* 1.事务采用的AOP 实现的  事务的注解必须在Public方法上
* 2.事务不能直接调用本类中方法 不然事务失效
* 3.事务失效还有可能是 数据库中的事务失效
* 4.需要执行事务回滚的具体异常类
* 5.子方法中不能吃了异常
```



数据同时修改可能出现的情况：

https://blog.csdn.net/qq_35493807/article/details/105790266

<iframe 
    height=450 
    width=800 
    src="https://blog.csdn.net/qq_35493807/article/details/105790266" 
    frameborder=0 
    allowfullscreen>
</iframe>



Springboot 事务的隔离级别和传播方式：

https://blog.csdn.net/qq_35493807/article/details/105790266

<iframe 
    height=450 
    width=800 
    src="https://blog.csdn.net/baidu_37107022/article/details/75578140?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-1.nonecase&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-1.nonecase" 
    frameborder=0 
    allowfullscreen>
</iframe>


接下来对于分布式条件 还有分布式事务