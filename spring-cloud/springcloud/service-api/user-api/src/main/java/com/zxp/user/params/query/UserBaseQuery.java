package com.zxp.user.params.query;

import com.zxp.user.params.UserBaseRegister;
import lombok.Data;


@Data
public class UserBaseQuery implements UserBaseRegister {
   private String phone;
   private String name;
   private String nickName;
}
