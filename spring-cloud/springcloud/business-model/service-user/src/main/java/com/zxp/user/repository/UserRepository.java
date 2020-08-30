package com.zxp.user.repository;

import com.zxp.user.dto.UserDTO;
import com.zxp.user.po.UserDO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<UserDO, String> {

    Optional<UserDTO> findByOpenID(String openid);
}
