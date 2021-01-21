package com.example.springstream;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * @author zhangxuepei
 * @since 3.0
 */
public interface ProducerChannelClient {
    /**
     * 数据域输出通道
     */
    String DOMAIN_OUTPUT = "Output";


    @Output(DOMAIN_OUTPUT)
    MessageChannel logoutput();
}
