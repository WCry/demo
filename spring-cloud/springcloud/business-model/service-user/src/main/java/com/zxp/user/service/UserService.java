package com.zxp.user.service;

import com.zxp.user.dto.UserDTO;
import com.zxp.user.params.UserParams;
import com.zxp.user.params.UserQueryParams;
import com.zxp.user.po.User;
import com.zxp.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;


public interface UserService {

    Optional<UserDTO> findById(String id);


    Boolean existsById(String id);


    Boolean existsByQuery(UserQueryParams userQueryParams);


    Boolean registerUser(User user);


    Boolean unRegisterUser(String id);
}
