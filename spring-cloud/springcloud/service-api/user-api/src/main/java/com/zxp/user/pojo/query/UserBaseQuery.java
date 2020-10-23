package com.zxp.user.pojo.query;

import com.zxp.user.pojo.UserBaseRegister;
import lombok.Data;


@Data
public class UserBaseQuery implements UserBaseRegister {
   private String phone;
   private String name;
   private String nickName;
}
