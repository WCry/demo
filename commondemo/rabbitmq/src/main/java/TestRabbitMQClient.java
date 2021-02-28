import com.rabbitmq.client.*;

/**
 * @author zhangxuepei
 * @since 3.0
 * chancel 在取消对应的消息者之后，未确认的消息依然能够进行消息确认
 */
public class TestRabbitMQClient {
    private static String host = "10.19.176.87";
    private static String userName = "guest";
    private static String passWord = "guest";
    private static int port = 5672;

    /**
     * @param args
     */
    public static void main(String[] args) {

        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost(host);
            factory.setPort(port);
            factory.setUsername(userName);
            factory.setPassword(passWord);
            //产生一个连接
            Connection connect = factory.newConnection();
            Channel channel = connect.createChannel();
            //            Channel channel2 = connect.createChannel();
            //声明队列 可以获取消费者数量等信息
            AMQP.Queue.DeclareOk declareOK = channel.queueDeclare("test", false, false, false, null);
            System.out.println(declareOK.getConsumerCount());

            //消费者绑定队列
            QueueingConsumer consumer = new QueueingConsumer(channel){
                public void handleCancelOk(String consumerTag) {
                    System.out.println("取消成功");
                }
            };

           // channel.basicQos(0,2,true);
            //采用拉的方式消费
            //channel.basicGet()
            //声明推送的消费方式 采用
            channel.basicQos(2);
            channel.basicConsume("test", false, consumer);


            //            //声明队列 可以获取消费者数量等信息
            //            AMQP.Queue.DeclareOk declareOK2 = channel2.queueDeclare("test",
            //                    false, false, false, null);
            //            System.out.println(declareOK.getConsumerCount());
            //            //消费者绑定队列
            //            QueueingConsumer consumer2 = new QueueingConsumer(channel2);
            //            //采用拉的方式消费
            //            //channel.basicGet()
            //            //声明推送的消费方式 采用
            //            channel2.basicConsume("test", false, consumer2);
            Boolean isCancel = false;


            String callbackQueueName = channel.queueDeclare().getQueue();
            com.rabbitmq.client.AMQP.BasicProperties props = new com.rabbitmq.client.AMQP.BasicProperties.Builder().replyTo(callbackQueueName)
                    .build();
            String message="dsad";
            channel.basicPublish("", "rpc_queue", props, message.getBytes());

            while (true) {
                //获取收到的推送消息
                QueueingConsumer.Delivery deliver = consumer.nextDelivery();
                System.out.println("reciver messager:" + new String(deliver.getBody()));
                if (!isCancel) {
                    channel.basicCancel(consumer.getConsumerTag());
                    isCancel = true;
                }
                Thread.sleep(1000);
                channel.basicAck(deliver.getEnvelope().getDeliveryTag(), false);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
