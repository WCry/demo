

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
 





