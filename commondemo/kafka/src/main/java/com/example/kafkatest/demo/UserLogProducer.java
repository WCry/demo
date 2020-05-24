package com.example.kafkatest.demo;

/**
 * Author: zhangxuepei
 * Date: 2020/3/19 19:54
 * Content:
 */

import com.example.kafkatest.demo.bean.UserLog;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.KafkaClient;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.sound.midi.Soundbank;

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
        int pa=1;
        System.out.println(Integer.valueOf(userid));
        if(Integer.valueOf(userid)%2==0){
            System.out.println("=========================222===");
            pa=2;
        }
        //设置两个分区
        kafkaTemplate.send("test",pa ,pa+userid,objectMapper.writeValueAsString(userLog));
       // System.out.println("分区数量："+kafkaTemplate.partitionsFor("test").size());

    }
}
