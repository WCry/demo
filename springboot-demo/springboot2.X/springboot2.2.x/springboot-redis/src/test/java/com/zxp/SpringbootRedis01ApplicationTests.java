package com.zxp;

import com.zxp.config.StringObjectRedisTemple;
import com.zxp.config.StringUserRedisTemplate;
import com.zxp.controller.RedisController;
import com.zxp.entity.po.User;
import com.zxp.utils.JsonUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootRedis01ApplicationTests {
    @Autowired
    private RedisController redisController;
    @Autowired
    private StringUserRedisTemplate stringUserRedisTemplate;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private StringObjectRedisTemple stringObjectRedisTemple;

    @Test
    public void testController() {
        redisController.test();
    }

    public void testStringWithJson() {
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
        stringUserRedisTemplate.opsForValue().set("json", user);
        User convertUser = (User) stringObjectRedisTemple.opsForValue().get("json");
        System.out.println(convertUser.toString());
    }

    @Test
    public void testRedisTemple() {
        User user = new User();
        user.setId(1);
        user.setUsername("张三");
        user.setPassword("abc");
        stringUserRedisTemplate.opsForValue().set("json", user);
        User jsonUser = stringUserRedisTemplate.opsForValue().get("json");
        System.out.println(jsonUser.toString());
    }

    @Test
    public void testRedisTempleSet() {
        String uerInfoKey = "user_info";
        //删除对应的key，所有的缓存时间都是相对于key进行设置
        stringUserRedisTemplate.delete(uerInfoKey);

        for (int i = 0; i < 2; i++) {
            User[] users = new User[8];
            for (int j = 0; j < 8; j++) {
                User user = new User();
                user.setId(j);
                int number = (i * 8 + j);
                if (i == 1) {
                    System.out.println("张三" + number);
                }
                user.setUsername("张三" + number);
                user.setPassword("abc");
                users[j] = user;
                if (i == 1) {
                    stringUserRedisTemplate.opsForSet().remove(uerInfoKey, user);
                }
            }
            //Redis对于set的hash相同取舍那个数据是随机的，Java的Set 中Hash中存在冲突是不进行替换
            //无法进行准确的替换
            stringUserRedisTemplate.opsForSet().add(uerInfoKey, users);
        }
        // pop操作取出对象，并且直接删除删除 弹出对象
        //stringUserRedisTemplate.opsForSet().pop(uerInfoKey,1);
        System.out.println("redis");
        for (User member : stringUserRedisTemplate.opsForSet().members(uerInfoKey)) {
            System.out.println(member.getUsername());
        }
    }

    @Test
    public void testRedisMoreKeys() {
        String uerInfoKey = "user_info";
        //删除对应的key，所有的缓存时间都是相对于key进行设置
        stringUserRedisTemplate.delete(uerInfoKey);

        for (int i = 0; i < 2; i++) {
            int count = 2;
            for (int j = 0; j < 1000000; j++) {
                User user = new User();
                user.setId(j);
                user.setUsername("张三" + j);
                user.setPassword("abc");
                if (i == 1) {
                    stringUserRedisTemplate.delete(uerInfoKey + count);
                }
                //Redis对于set的hash相同取舍那个数据是随机的，Java的Set 中Hash中存在冲突是不进行替换
                stringUserRedisTemplate.opsForValue().set(uerInfoKey + count, user);
            }
        }
        //不是很好获取一段时间的全部对象
        stringUserRedisTemplate.opsForValue();
        for (User member : stringUserRedisTemplate.opsForSet().members(uerInfoKey)) {
            System.out.println(member.getUsername());
        }
    }


    @Test
    public void testRedisTempleGeo() {
        String uerInfoKey = "user_info_point";
        stringUserRedisTemplate.delete(uerInfoKey);
        User liSi = new User();
        liSi.setId(1);
        liSi.setUsername("liSi");
        stringUserRedisTemplate.opsForGeo().add(uerInfoKey, new Point(120, 30), liSi);
        System.out.println(stringUserRedisTemplate.opsForGeo().position(uerInfoKey, liSi).get(0));
    }
}
