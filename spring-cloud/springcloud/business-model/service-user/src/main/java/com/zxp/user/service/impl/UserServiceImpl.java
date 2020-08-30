package com.zxp.user.service.impl;

import com.zxp.user.dto.UserDTO;
import com.zxp.user.params.UserQueryParams;
import com.zxp.user.po.UserDO;
import com.zxp.user.repository.UserRepository;
import com.zxp.user.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<UserDTO> findById(String id) {
        return this.userRepository.findByOpenID(id);
    }

    @Override
    public Boolean existsById(String id) {
        return userRepository.existsById(id);
    }

    @Override
    public Boolean existsByQuery(UserQueryParams userQueryParams) {
        return null;
    }

    @Override
    public Boolean registerUser(UserDO userDO) {
        userRepository.save(userDO);
        return true;
    }

    @Override
    public Boolean unRegisterUser(String id) {
        userRepository.deleteById(id);
        return null;
    }
}
