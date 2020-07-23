
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
 rabbitTemplate.setUsePublisherConnection(true);

RabbitMQ的ack或nack机制使用不当导致的队列堵塞或死循环问题
https://blog.csdn.net/youbl/article/details/80425959?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-2.channel_param&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-2.channel_param


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