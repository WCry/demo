package com.zxp.user.params.update;

import com.zxp.user.params.UserBaseRegister;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserBaseRegisterParams implements UserBaseRegister {
    private String nickName;
    private String name;
    private String phone;
}
