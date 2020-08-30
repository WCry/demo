package com.zxp.user.service;

import com.zxp.user.dto.UserDTO;
import com.zxp.user.params.UserParams;
import com.zxp.user.params.UserQueryParams;
import com.zxp.user.po.User;
import com.zxp.user.repository.UserRepository;
import com.zxp.user.vo.UserVo;
import org.springframework.stereotype.Service;

import java.util.Optional;


public interface UserService {

    Optional<UserVo> findUserVOById(String id);

    Optional<UserDTO> findUserDTOById(String id);


    Boolean existsById(String id);


    Boolean existsByQuery(UserQueryParams userQueryParams);


    Boolean registerUser(User user);


    Boolean unRegisterUser(String id);
}
