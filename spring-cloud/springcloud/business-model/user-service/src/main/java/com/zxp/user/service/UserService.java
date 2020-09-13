package com.zxp.user.service;

import com.zxp.user.params.dto.UserDTO;
import com.zxp.user.params.query.UserBaseQuery;
import com.zxp.user.params.query.UserIdentifyQuery;
import com.zxp.user.params.register.UserRegisterParams;
import com.zxp.user.po.UserDO;
import org.springframework.lang.Nullable;

import java.util.Optional;


public interface UserService {

    Optional<UserDTO> findUserDTOById(String id);

    Boolean existsById(String id);

    Boolean existsByQuery(UserBaseQuery userBaseQuery);

    Boolean registerUser(UserRegisterParams userRegisterParams, Optional<UserIdentifyQuery> userIdentifyQuery);

    Boolean unRegisterUser(String id);
}
