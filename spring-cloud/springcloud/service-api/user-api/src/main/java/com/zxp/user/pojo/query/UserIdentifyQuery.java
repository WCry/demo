package com.zxp.user.pojo.query;

import com.zxp.user.pojo.UserIdentifies;
import lombok.Data;


@Data
public class UserIdentifyQuery implements UserIdentifies {
   private String openID;
}
