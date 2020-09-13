package com.zxp.user.repository;

import com.zxp.user.po.UserDO;
import com.zxp.user.params.UserBase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<UserDO, String> {

    Optional<UserBase> findByOpenID(String openid);
}
