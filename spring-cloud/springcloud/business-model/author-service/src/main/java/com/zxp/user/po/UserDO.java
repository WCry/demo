package com.zxp.user.po;

import com.zxp.user.params.UserIdentifies;
import com.zxp.user.params.UserBaseRegister;
import com.zxp.user.params.UserSecurity;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@ToString
@Data
@Entity
@Table(schema = "db_account",name = "tb_user")
@EntityListeners(AuditingEntityListener.class)
public class UserDO implements Serializable, UserBaseRegister, UserIdentifies, UserSecurity {

	@Id
	private String openID;

    @Column(name = "user_account")
	private String userAccount;//用户名

    @Column(name = "password")
	private String password;//密码，加密存储

    @Column(name = "phone")
	private String phone;//注册手机号

    @Column(name = "created")
	@CreatedDate
	private Date created;//创建时间

	@LastModifiedDate
    @Column(name = "updated")
	private Date updated;//修改时间

    @Column(name = "nick_name")
	private String nickName;//昵称

    @Column(name = "name")
	private String name;//真实姓名

    @Column(name = "status")
	private String status;//使用状态（1正常 0非正常）

    @Column(name = "head_pic")
	private String headPic;//头像地址

	//采用0和1表示Boolean值
    @Column(name = "is_mobile_check",columnDefinition = "bit(1) default 0")
	private Boolean isMobileCheck;//手机是否验证

    @Column(name = "is_email_check",columnDefinition = "bit(1) default 0")
	private Boolean isEmailCheck;//邮箱是否检测

    @Column(name = "sex")
	private String sex;//性别，1男，0女

    @Column(name = "birthday")
	private Date birthday;//出生年月日

}
