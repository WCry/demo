package com.zxp.user.service;

import com.zxp.user.pojo.UserSecurity;
import com.zxp.user.pojo.dto.UserDTO;
import com.zxp.user.pojo.query.UserAccountQuery;
import com.zxp.user.pojo.query.UserBaseQuery;
import com.zxp.user.pojo.query.UserIdentifyQuery;
import com.zxp.user.pojo.update.UserSecurityParams;

import java.util.Optional;


/**
 * 用户服务 数据持久层和展示层的防腐层
 */
public interface UserService {

    Optional<UserDTO> findUserDTOById(UserIdentifyQuery id);

    Optional<UserSecurity> findUserSecurityDTOById(UserIdentifyQuery id);

    Optional<UserSecurity> findUserSecurityDTOByUserAccount(UserAccountQuery userAccountQuery);

    Boolean existsById(UserIdentifyQuery id);

    Boolean existsByQuery(UserBaseQuery userBaseQuery);

    Boolean registerUser(UserSecurityParams userRegisterParams);

    Boolean updateUser(UserIdentifyQuery userIdentifyQuery, UserSecurityParams userRegisterParams);

    Boolean unRegisterUser(String id);
}
