package com.zxp.user.service;

import com.zxp.user.dto.UserDTO;
import com.zxp.user.params.UserQueryParams;
import com.zxp.user.po.UserDO;

import java.util.Optional;


public interface UserService {

    Optional<UserDTO> findUserDTOById(String id);

    Boolean existsById(String id);

    Boolean existsByQuery(UserQueryParams userQueryParams);

    Boolean registerUser(UserDO userDO);

    Boolean unRegisterUser(String id);
}
