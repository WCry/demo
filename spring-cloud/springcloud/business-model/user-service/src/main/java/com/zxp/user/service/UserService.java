package com.zxp.user.service;

import com.zxp.user.params.dto.UserDTO;
import com.zxp.user.params.query.UserBaseQuery;
import com.zxp.user.params.query.UserIdentifyQuery;
import com.zxp.user.params.update.UserRegisterParams;

import java.util.Optional;


public interface UserService {

    Optional<UserDTO> findUserDTOById(String id);

    Boolean existsById(String id);

    Boolean existsByQuery(UserBaseQuery userBaseQuery);

    Boolean registerUser(UserRegisterParams userRegisterParams);

    Boolean updateUser(UserIdentifyQuery userIdentifyQuery,UserRegisterParams userRegisterParams);

    Boolean unRegisterUser(String id);
}
