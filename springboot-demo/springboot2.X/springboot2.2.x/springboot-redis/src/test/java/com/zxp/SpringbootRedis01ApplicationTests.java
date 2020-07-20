package com.zxp;

import com.zxp.controller.RedisController;
import com.zxp.entity.po.User;
import com.zxp.utils.JsonUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootRedis01ApplicationTests {
    @Autowired
    public RedisController redisController;
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Test
    public void testController() {
        redisController.test();
    }

    @Test
    public void testRedisTemple() {
        redisController.test();
        User user = new User();
        user.setId(1);
        user.setUsername("张三");
        user.setPassword("abc");
        redisTemplate.opsForValue().set("json", JsonUtils.objectToJson(user));
        User jsonUser = JsonUtils.jsonToPojo(redisTemplate.opsForValue().get("json"), User.class);
        System.out.println(jsonUser.toString());
    }
}
