Redis介绍:

Redis主要应用：分布式缓存，相对于Memchcare的数据类型多。

Redis是采用基于C/S模式的请求/响应协议的TCP服务器。redis客户端通过socket连接发起请求，每个请求在命令触发后会阻塞等待redis服务器进行处理，处理完毕后将结果返回给client。每个请求都存在往返时间，即使redis性能高，当数据量大时也会极大影响性能，还可能引起其他意外情况。

在很多场景下，我们要完成一个业务，可能会对redis做连续多个操作。譬如库存减一，订单加一等等，有很多步骤需要连续依次执行。这种场景下，网络传输的耗时将是限制redis处理量的主要瓶颈。

使用Pipeline可以解决以上问题：Pipeline把所有命令一次性发过去，避免频繁的发送、接收带来的网络开销。redis在打包接收到一堆命令后，依次执行，然后把执行结果再打包返回给客户端。



Redis客户端：

Jedis客户端原生的是线程不安全吗，JedisPool连接资源池，可以实现Jedis客户端的管理。

Lettuce是线程安全的，可以进行异步操作，采用nettry作为底层的网络通信。





当前Spring Boot  1.X中默认集成的是Jedis的客户端

Spring Boot 2.X中默认集成的是Lettuce客户端

RedisTemple是线程安全的

# springboot-redis01

**springboot整合redis**

## springboot Redis关键点
    1、在pom.xml文件中添加依赖
        <!-- redis -->
        <dependency>
             <groupId>org.springframework.boot</groupId>
             <artifactId>spring-boot-starter-data-redis</artifactId>
        </dependency>
    2、在application.properties文件中配置redis配置（不同springboot的版本redis的配置可能不一样）
        spring.redis.database=1
        spring.redis.host=127.0.0.1
        spring.redis.port=6379
        spring.redis.password=
        spring.redis.jedis.pool.max-active=1000
        spring.redis.jedis.pool.max-wait=-1ms
        spring.redis.jedis.pool.max-idle=10
        spring.redis.jedis.pool.min-idle=2
        spring.redis.timeout=500ms
        
    3、在RedisService接口中声明redis的常用操作
    
    4、在RedisServiceImpl接口实现类中实现RedisService接口，注入StringRedisTemplate Api接口，
    通过调用StringRedisTemplate中的方法对redis进行操作
        @Autowired
        private StringRedisTemplate redisTemplate;
    RedisTemple的源码解读，Redis


SpringBoot的Redis源码解析的过程中源码疑惑的地方。RedisTemple是线程安全的。底层连接管理采用的是TransactionSynchronizationManager进行管理的连接的，内部使用ThreadLocal进行线程安全的管理。



主要是在Redis使用模板的时候返回值是空，这个不影响。RedisTemple的所有直接简化的操作是非管道操作吗，只有主动调用管道操作的采用管道操作，redisTemplate.exec()。用于获取管道执行结果。









参考文档：

https://blog.csdn.net/zzhongcy/article/details/102584028