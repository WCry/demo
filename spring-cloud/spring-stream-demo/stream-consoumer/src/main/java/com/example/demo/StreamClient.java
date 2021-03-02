package com.example.demo;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author zhangxuepei
 * @since 3.0
 */
public interface StreamClient {
        //报错：Invalid bean definition with name 'myMessageOrdersssss' defined in com.imooc.order.message.StreamClient: bean definition with this name already exists
        //解决方法：@Input和@Output不可一样，同一服务里面的信道名字不能一样，在不同的服务里可以相同名字的信道
        String INPUT = "input";
        String OUTPUT = "output";
        @Input(StreamClient.INPUT)
        SubscribableChannel input();
        @Output(StreamClient.OUTPUT)
        MessageChannel output();

        String INPUT2 = "input2";
        String OUTPUT2 = "output2";
        @Input(StreamClient.INPUT2)
        SubscribableChannel input2();
        @Output(StreamClient.OUTPUT2)
        MessageChannel output2();
}
