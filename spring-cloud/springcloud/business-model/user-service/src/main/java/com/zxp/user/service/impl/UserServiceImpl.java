package com.zxp.user.service.impl;

import com.zxp.user.params.UserBase;
import com.zxp.user.params.dto.UserDTO;
import com.zxp.user.params.query.UserBaseQuery;
import com.zxp.user.params.query.UserIdentifyQuery;
import com.zxp.user.params.update.UserRegisterParams;
import com.zxp.user.po.UserDO;
import com.zxp.user.repository.UserRepository;
import com.zxp.user.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<UserDTO> findUserDTOById(String id) {
        Optional<UserBase> result = userRepository.findByOpenID(id);
        return result.map(v -> {
            UserDTO userDTO = new UserDTO();
            BeanUtils.copyProperties(v, userDTO);
            return Optional.of(userDTO);
        }).orElse(Optional.empty());
    }

    @Override
    public Boolean existsById(String id) {
        return userRepository.existsById(id);
    }

    @Override
    public Boolean existsByQuery(UserBaseQuery userBaseQuery) {
        return null;
    }


    @Override
    public Boolean registerUser(UserRegisterParams userRegisterParams) {
        UserDO userDO = new UserDO();
        userDO.setOpenID(UUID.randomUUID().toString());
        BeanUtils.copyProperties(userRegisterParams, userDO);
        userRepository.save(userDO);
        return true;
    }

    @Override
    public Boolean updateUser(UserIdentifyQuery userIdentifyQuery,UserRegisterParams userRegisterParams) {
        Optional<UserDO> userDOOptional = userRepository.findById(userIdentifyQuery.getOpenID());
        return userDOOptional.map(v -> {
            BeanUtils.copyProperties(userRegisterParams, v);
            userRepository.save(v);
            return true;
        }).orElse(false);
    }
    @Override
    public Boolean unRegisterUser(String id) {
        try {
            userRepository.deleteById(id);
            return true;
        } catch (EmptyResultDataAccessException ex) {
            return false;
        }
    }
}
