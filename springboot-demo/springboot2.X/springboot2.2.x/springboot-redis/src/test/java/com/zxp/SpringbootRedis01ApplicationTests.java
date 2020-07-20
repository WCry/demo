package com.zxp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zxp.config.StringObjectRedisTemple;
import com.zxp.config.StringUserRedisTemple;
import com.zxp.controller.RedisController;
import com.zxp.entity.po.User;
import com.zxp.entity.vo.ApiResponse;
import com.zxp.utils.JsonUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootRedis01ApplicationTests {
    @Autowired
    private RedisController redisController;
    @Autowired
    private StringUserRedisTemple stringUserRedisTemple;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private  StringObjectRedisTemple stringObjectRedisTemple;
    @Test
    public void testController() {
        redisController.test();
    }

    public void testStringWithJson(){
        User user = new User();
        user.setId(1);
        user.setUsername("张三");
        user.setPassword("abc");
        redisTemplate.opsForValue().set("json", JsonUtils.objectToJson(user));
        //采用JsonUtils实现返回传入类对象，返回对应的类，没有在redisTemplate
        User jsonUser = JsonUtils.jsonToPojo(redisTemplate.opsForValue().get("json"), User.class);
        System.out.println(jsonUser.toString());
    }
    @Test
    public void testObjectTemple() {
        User user = new User();
        user.setId(1);
        user.setUsername("张三");
        user.setPassword("abc");
        stringUserRedisTemple.opsForValue().set("json", user);
        User convertUser= (User)stringObjectRedisTemple.opsForValue().get("json");
        System.out.println(convertUser.toString());
    }

    @Test
    public void testRedisTemple() {
        User user = new User();
        user.setId(1);
        user.setUsername("张三");
        user.setPassword("abc");
        stringUserRedisTemple.opsForValue().set("json", user);
        User jsonUser = stringUserRedisTemple.opsForValue().get("json");
        System.out.println(jsonUser.toString());
    }
}
