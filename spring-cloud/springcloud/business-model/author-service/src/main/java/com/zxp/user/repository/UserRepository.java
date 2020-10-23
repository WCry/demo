package com.zxp.user.repository;

import com.zxp.user.pojo.UserSecurity;
import com.zxp.user.pojo.dto.UserDTO;
import com.zxp.user.po.UserDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface UserRepository extends JpaRepository<UserDO, String> {

    @Query("select  new com.zxp.user.pojo.dto.UserDTO(u.openID,u.nickName) from  UserDO u where u.openID=?1")
    Optional<UserDTO> findUserDTOByOpenID(String openid);

    @Query("select  new com.zxp.user.pojo.update.UserSecurityParams(u.userAccount,u.password,u.phone) from  UserDO u where u.openID=?1")
    Optional<UserSecurity> findUserSecurityByOpenID(String openid);

    @Query("select  new com.zxp.user.pojo.update.UserSecurityParams(u.userAccount,u.password,u.phone) from  UserDO u where u.userAccount=?1")
    Optional<UserSecurity> findUserSecurityByUserAccount(String userAccount);
}
