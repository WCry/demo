package com.zxp.user.params.query;

import com.zxp.user.params.UserIdentifies;
import lombok.Data;


@Data
public class UserIdentifyQuery implements UserIdentifies {
   private String openID;
}
