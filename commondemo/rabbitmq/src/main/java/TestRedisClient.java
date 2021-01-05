import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

/**
 * @author zhangxuepei
 * @since 3.0
 */
public class TestRedisClient {
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
            Channel channel2 = connect.createChannel();
            //声明队列 可以获取消费者数量等信息
            AMQP.Queue.DeclareOk declareOK = channel.queueDeclare("test",
                    false, false, false, null);
            System.out.println(declareOK.getConsumerCount());

            //消费者绑定队列
            QueueingConsumer consumer = new QueueingConsumer(channel);
            //采用拉的方式消费
            //channel.basicGet()
            //声明推送的消费方式 采用
            channel.basicConsume("test", true, consumer);
            //声明队列 可以获取消费者数量等信息
            AMQP.Queue.DeclareOk declareOK2 = channel2.queueDeclare("test",
                    false, false, false, null);
            System.out.println(declareOK.getConsumerCount());
            //消费者绑定队列
            QueueingConsumer consumer2 = new QueueingConsumer(channel2);
            //采用拉的方式消费
            //channel.basicGet()
            //声明推送的消费方式 采用
            channel2.basicConsume("test", true, consumer2);
            while (true) {
                //获取收到的推送消息
                QueueingConsumer.Delivery deliver = consumer.nextDelivery();
                System.out.println("reciver messager:"+new String(deliver.getBody()));
                QueueingConsumer.Delivery deliver2=consumer2.nextDelivery();
                System.out.println("reciver messager:"+new String(deliver2.getBody()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
