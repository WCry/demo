package com.zxp.user.repository;

import com.zxp.user.po.UserDO;
import com.zxp.user.po.UsrNameNickNameOpenID;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<UserDO, String> {

    Optional<UsrNameNickNameOpenID> findByOpenID(String openid);
}
