package com.zxp.user.test;


import com.zxp.user.UserApplication;
import com.zxp.user.dto.UserDTO;
import com.zxp.user.feign.UserServiceFeign;
import com.zxp.user.po.UserDO;
import com.zxp.user.po.UsrNameNickNameOpenID;
import com.zxp.user.repository.UserRepository;
import com.zxp.user.resoponse.Result;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest(classes = UserApplication.class)
public class TestAddUserInfo {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserServiceFeign userServiceFeign;

    @Test
    public void testQuery(){
        String openID="4ea6f233-08e7-4d18-a02f-35a805c13e6e";
        Result<Optional<UserDTO>> result= userServiceFeign.findById(openID);
        System.out.println(result.getData().get());
    }


    @Test
    public void addUser() throws ParseException {
        UserDO userDO = new UserDO();
        String birthday = "1991-07-30";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date birDate = sdf.parse(birthday);
        String openID = UUID.randomUUID().toString();
        userDO.setOpenID(openID);
        userDO.setBirthday(birDate);
        userDO.setSex("男");
        userDO.setName("张三");
        userDO.setNickName("无误");
        userDO.setPhone("15138901140");
        userDO.setStatus("1");
        userDO.setPassword("123456");
        userDO.setHeadPic("www.ddd.ddd");
        userDO.setIsEmailCheck(false);
        userDO.setIsMobileCheck(false);
        UserDO ds = userRepository.save(userDO);
        userRepository.flush();
        Optional<UsrNameNickNameOpenID> dds = userRepository.findByOpenID(openID);
        System.out.println(dds.get());
        System.out.println(userRepository.findById(openID).get());
    }
}
