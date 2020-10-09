package com.zxp.seckill.pojo.dto;

import com.zxp.seckill.pojo.SecKillGoodIdentify;
import com.zxp.seckill.pojo.SecKillVerification;
import com.zxp.user.params.UserIdentifies;
import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class VerificationSecSkillOrder implements SecKillGoodIdentify, UserIdentifies, SecKillVerification {
    private String openID;
    private Long secKillID;
    private String verificationID;
    private String verificationCode;

}
