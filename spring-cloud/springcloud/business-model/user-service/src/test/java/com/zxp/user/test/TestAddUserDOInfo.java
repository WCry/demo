package com.zxp.user.test;


import com.zxp.user.UserApplication;
import com.zxp.user.feign.UserServiceFeign;
import com.zxp.user.params.query.UserIdentifyQuery;
import com.zxp.user.po.UserDO;
import com.zxp.user.repository.UserRepository;
import com.zxp.user.resoponse.Result;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest(classes = UserApplication.class)
public class TestAddUserDOInfo {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserServiceFeign userServiceFeign;

    @Test
    public void testQueryByIdentify(){
        String openID="e927d2ea-a460-4563-afd2-6a05e4567439";
        System.out.println(userRepository.findUserDTOByOpenID(openID));
        //System.out.println(userRepository.findBydsdsOpenID(openID));

//        UserIdentifyQuery userIdentifyQuery=new UserIdentifyQuery();
//        userIdentifyQuery.setOpenID(openID);
//        Result<Optional<UserDTO>> result= userServiceFeign.queryUserByIdentify(userIdentifyQuery);
//        System.out.println(result.getData().get().getClass());
//        System.out.println(result.getData().get());
    }
    @Test
    public void testDeleteByIdentify(){
        String openID="4ea6f233-08e7-4d18-a02f-35a805c13e6e";
        UserIdentifyQuery userIdentifyQuery=new UserIdentifyQuery();
        userIdentifyQuery.setOpenID(openID);
        Result<Boolean> result= userServiceFeign.unRegisterUser(userIdentifyQuery);
        System.out.println(result.getData());
    }


    @Test
    public void addUser() throws ParseException {
        UserDO userDO = new UserDO();
        String birthday = "1991-07-30";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date birDate = sdf.parse(birthday);
        String openID = UUID.randomUUID().toString();
        //openID="46407579-b47f-4687-a593-c41eaefeacc0";
        userDO.setOpenID(openID);
        userDO.setBirthday(birDate);
        userDO.setSex("男");
        userDO.setName("张三");
        userDO.setNickName("无误");
        userDO.setPhone("15138901140");
        userDO.setUserAccount("15138901140");
        userDO.setStatus("1");
        userDO.setPassword("123456");
        userDO.setHeadPic("www.ddd.ddd");
        userDO.setIsEmailCheck(false);
        userDO.setIsMobileCheck(false);
        UserDO ds = userRepository.save(userDO);
        userRepository.flush();
        Optional<UserDO> dds = userRepository.findById(openID);
        System.out.println(dds.get());
        System.out.println(userRepository.findById(openID).get());
    }
}
