package com.zxp.component;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.zxp.entry.User;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class JsonCustomerDeserializer extends JsonDeserializer<User> {

    @Override
    public User deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
       //暂时不清楚这个怎么设置  暂时是获取TreeNode方式 然后
        TreeNode treeNode = p.getCodec().readTree(p);
        User user = new User();
        user.setName("dsad");
        return user;
    }
}