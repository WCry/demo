package com.zxp.user.pojo.update;

import com.zxp.user.pojo.UserBaseRegister;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserBaseRegisterParams implements UserBaseRegister {
    private String nickName;
    private String name;
    private String phone;
}
