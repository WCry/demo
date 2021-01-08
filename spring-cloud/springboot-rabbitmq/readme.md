
rabbitMq官网
https://www.rabbitmq.com/production-checklist.html

rabbitMQ 下载安装

https://www.rabbitmq.com/releases/rabbitmq-server/v3.6.5/

erlange下载安装

https://www.rabbitmq.com/releases/erlang/

socat下载，一个Linux的网络工具。

## [socat](http://repo.iotti.biz/CentOS/7/x86_64/socat-1.7.3.2-5.el7.lux.x86_64.rpm)

rpm   先安装erlang

安装socat

安装rabbitmq

rpm -ivh rpm包



erlang 安装

http://erlang.org/download/

https://www.erlang.org/downloads/20.0

RabbitMQ的git 仓库：发布变化和环境要求

https://github.com/rabbitmq/rabbitmq-server/releases



https://my.oschina.net/foxneil/blog/2877079

实际使用yum 命令进行安装也是比较简单的，在公司无法访问网络的情况下安装比较麻烦。

依赖需要自己判断，按照顺序进行安装。



安装erlang 需要选择在线的方式进行安装， 不然依赖折磨的头疼

https://blog.csdn.net/fan521dan/article/details/104930982

https://blog.csdn.net/fan521dan/article/details/104930982

https://blog.csdn.net/qq_35387940/article/details/100514134

https://docs.spring.io/spring-boot/docs/current/reference/html/spring-boot-features.html#boot-features-nosql

https://blog.csdn.net/weixin_43343423/article/details/103382661





编辑内容如下：

```
[{rabbit, [{loopback_users, []}]}].
```

这里的意思是开放使用，rabbitmq默认创建的用户guest，密码也是guest，这个用户默认只能是本机访问，localhost或者127.0.0.1，从外部访问需要添加上面的配置。

保存配置后重启服务：

```
service rabbitmq-server stop
service rabbitmq-server start
```

```
#开启管理UI：
rabbitmq-plugins enable rabbitmq_management
```

Springboot使用rabbitMq的maven依赖
<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-amqp</artifactId>
		</dependency>

rabbitMq 声明队列方式，主要使用在provider端声明队列，避免写太多的Bean。
@RabbitListener(bindings = @QueueBinding(exchange =
    @Exchange(value = "topic.exchange", type = "topic"),
            value = @Queue(value = "consumer_queue4", durable = "true"), key = "key.#"))
自己声明队列进行绑定
@Bean
    private Queue queueA() {
        return new Queue("fanout.A");
    } 
@Bean
        FanoutExchange fanoutExchange() {
            return new FanoutExchange("fanoutExchange");
        } 
 采用admin管理器进行声明队列，交换器进行绑定                 
 amqpAdmin.declareExchange( new DirectExchange( "exchange.direct") );           
 amqpAdmin.declareQueue( new Queue( "direct.queue" , true ) );
 amqpAdmin.declareBinding( new Binding( "direct.queue" , Binding.DestinationType.QUEUE , "exchange.direct" , "direct.queue" , null ) );

 对于消费者可以只声明队列，暂时不进行绑定到交换器上。如果只用名称，则队列的名称必须已经存在           
 @RabbitListener(queuesToDeclare = @Queue(value = "consumer_queue5"))
 只使用字符串，队列名称是必须已经存在的
  @RabbitListener(queues = "consumer_queue4")


 header交换器和队列使用比较少
 消息发送到交换机确认机制,是否返回回馈
 publisher-returns单一设置就开启发送端确认模式
 或者单一个开启publisher-confirm-type=correlated
开启采用correlated模式手动确认，此时需要发送方法中包括CorrelationData参数
  CorrelationData主要是设置发送端设置的消息ID，可以通过ID知道哪些消息成功和失败
  publisher-confirm-type=simple模式表明自己调用
    rabbitTemplate.invoke()
    rabbitTemplate.waitForConfirms(10);
  进行自定义手动确认方式
  publisher-confirm-type=none 表示不需要发送方进行确认
  spring.rabbitmq.publisher-confirm-type=correlated
  publisher-returns暂时不清楚为什么这个总开关意图
  spring.rabbitmq.publisher-returns= true
  只有设置成为True时候消息在未进入队列时候进行返回
  spring.rabbitmq.template.mandatory=true


 针对于发布者新建一个链接，发布者和监听采用不同链接不相互影响
 主要是RabbitMQ采用ELarg语言，进程间授信机制，如果不分开，
 链接没有分开，发送阻塞的同时也会阻塞掉读取的连接。
 rabbitTemplate.setUsePublisherConnection(true);

RabbitMQ的ack或nack机制使用不当导致的队列堵塞或死循环问题
https://blog.csdn.net/youbl/article/details/80425959?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-2.channel_param&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-2.channel_param

MQ中间件-rabbitmq-消费者消息获取及异常处理的实现（SpringBoot2.0环境下）
https://www.jianshu.com/p/090ed51006d5
RabbitMQ中的流控限制
https://blog.csdn.net/qq_20892953/article/details/80593282
Rabbitmq超过内存限制，进行流控限制
https://blog.csdn.net/linzhiqiang0316/article/details/90738856
Rabbitmq关于队列中消息积压影响消费速率
https://blog.csdn.net/qq_20892953/article/details/80617305?fps=1&locationNum=2

对于生产者需要监控绑定Mq消息阻塞事件，放置继续持续的往里面写入数据

QoS进行流控
https://www.jianshu.com/p/ae38e29f7db1

1千万消息积压大概占用内存40个G
1千万消息按照正常消费大概需要两个小时

rabbitmq监听Rabbitmq的服务器上消息：
打开RabbitMq事件插件
https://www.rabbitmq.com/event-exchange.html
注册监听RabbitMq服务daunt事件Bean
BrokerEventListener
开启Springboot事件监听监听BrokerEvent事件获取来自于RabbitMq服务端的事件
还可以使用这样监听
Springboot 通过ConnectionBlockedListener将阻塞事件转换成为Springboot 的系统事件
ConnectionUnblockedEvent
ConnectionBlockedEvent

如果RabbitMQ的能力达不到要求，换用kafka消息队里，kafka的吞吐量是RabbitMq的十倍





```java
#用来处理注解处理
RabbitListenerAnnotationBeanPostProcessor

#Rabbitmq的消费重试次数，是指在消费的客户端，进行消费重试。不是指从Brocker中去取数据

```



RabbitMQ手动确认消费：

```
channel
```





Springboot 消息手动确认模式：

1.监听方法需要参数channel进行去人消息，包括消息成功或者消费失败。
2.不确认也无异常，消息不会自动重新推送。
3.rabbitmq断开连接，消息会重新推送
4.spring.rabbitmq.listener.retry配置的重发是在消费端应用内处理的，不是rabbitqq重发。消息重试次数用完完之后，消息不会重发。
可以配置MessageRecoverer对异常消息进行处理，此处理会在5.listener.retry次数尝试完并还是抛出异常的情况下才会调用，默认有两个实现：RepublishMessageRecoverer将消息发送到指定队列，RejectAndDontRequeueRecoverer如果不手动配置MessageRecoverer，会默认使用这个，实现仅仅是将异常打印抛出。


Springboot：
@sendto注解：消息经过一个消费完成之后，将结果转发到下一个消息处理过程。@sendto的值采用 exchangeName/routekey 的方式。同时支持spel表达式的方式书写

https://docs.spring.io/spring-amqp/docs/2.2.9.RELEASE/reference/html/#choose-container

RabbitMQ支持异步处理

AsyncRabbitTemplate asyncRabbitTemplate;


RbbitMQ的带有回复功能的接口采用的默认回复队列名称，调用不需要显示的创建回复队列

主要针对SpringBoot的**convertAndSend**和**convertSendAndReceive**两个方法：

https://blog.csdn.net/fan521dan/article/details/104930982

在这里可以看到使用的是推送的方式进行获取消息
BlockingQueueConsumer的setQosAndreateConsumers方法
SpringBoot的默认预取数量是250个消息
ChannelN
异步处理消息主循环处理操作线程
AsyncMessageProcessingConsumer的mainLoop循环

RabbitMQ的MessageProperties的getDeliveryTag是每一个会话从1开始的
RabbitMQ的消费端，每一次都是从最新的一个消息进行消费的，消息被确认之后，消息将被
RabbitMQ的服务标记为删除，等到一定程度在进行删除。
MessageID采用整个程序的消息的ID


RabbitMQ的Lazy Queue队列保证，消息存储在磁盘上，减少内存，应对大量消息积压的情况。
1千万消息，普通模式占用内存大概是1.2G，但是使用lazy模式占用内存大概是1.5M。
https://blog.csdn.net/u013256816/article/details/77987216


RabbitMQ采用追加的方式增加消息到文件的末尾
RabbitMQ的重放回队列的策略是，将消息重新放到消息队列开头。
这条消息可能会被很快再次拿到，再次进行消费。这样操作能够保证消息按照顺序进行消费，但是对于普通的消息，
但是如果处理不当可能造成重复放回队列，造成消息阻塞。重新放回队列，可以会被其他消费者消费掉。

RabbitMQ多个消费者可以采用公平的方式进行消费或者：
RabbitMQ如果消费端不设置预取数量，将会一直轮询方式向多个消费端发送消息。
如果设置预提取数量，将会采用公平的方式进行分配给多个消费者。

RabbitMQ的消息元数据存储是在ETS表，当数据到达时候插入元数据记录，读取确认之后删除元数据记录。同时标记数据。
https://blog.csdn.net/yongche_shi/article/details/51500534

RabbitMQ实战：
https://blog.csdn.net/u013256816/article/details/77987216


RabbitMQ 的Stop时候不要强制关闭连接
messageListenerContainer.setForceCloseChannel(false);