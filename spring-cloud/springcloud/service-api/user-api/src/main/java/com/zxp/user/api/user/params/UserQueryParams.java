package com.zxp.user.api.user.params;

import lombok.Data;


@Data
public class UserQueryParams {
   private String userID;
   private String phoneNumber;
   private String qqMail;
}
