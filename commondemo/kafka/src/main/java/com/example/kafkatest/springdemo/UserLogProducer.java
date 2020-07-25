package com.example.kafkatest.springdemo;

/**
 * Author: zhangxuepei
 * Date: 2020/3/19 19:54
 * Content:
 */

import com.example.kafkatest.springdemo.bean.UserLog;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class UserLogProducer {
    @Autowired
    private KafkaTemplate kafkaTemplate;

    public void sendLog(String userid) throws JsonProcessingException {
        UserLog userLog = new UserLog();
        userLog.setUsername("jhp");
        userLog.setUserid(userid);
        userLog.setState("0");
        //System.err.println("发送用户日志数据:"+userLog);
        ObjectMapper objectMapper = new ObjectMapper();
        //设置两个分区
        kafkaTemplate.send("test22",userid,objectMapper.writeValueAsString(userLog));
    }
}
