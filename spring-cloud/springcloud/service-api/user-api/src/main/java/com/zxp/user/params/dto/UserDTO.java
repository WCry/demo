package com.zxp.user.params.dto;

import com.zxp.user.params.UserBase;
import com.zxp.user.params.UserIdentifies;
import lombok.Data;


@Data
public class UserDTO implements UserBase, UserIdentifies {
    private String openID;
    private String nickName;
}
