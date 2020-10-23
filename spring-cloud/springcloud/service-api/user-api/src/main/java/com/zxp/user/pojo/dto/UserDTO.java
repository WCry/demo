package com.zxp.user.pojo.dto;

import com.zxp.user.pojo.UserBase;
import com.zxp.user.pojo.UserIdentifies;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class UserDTO implements UserBase, UserIdentifies {
    private String openID;
    private String nickName;
}
