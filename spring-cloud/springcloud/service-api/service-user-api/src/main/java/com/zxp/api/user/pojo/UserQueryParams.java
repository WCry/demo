package com.zxp.api.user.pojo;

import lombok.Data;


@Data
public class UserQueryParams {
   private String userID;
   private String phoneNumber;
   private String qqMail;
}
