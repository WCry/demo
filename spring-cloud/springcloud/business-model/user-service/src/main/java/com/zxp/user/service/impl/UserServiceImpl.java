package com.zxp.user.service.impl;

import com.zxp.user.params.UserSecurity;
import com.zxp.user.params.dto.UserDTO;
import com.zxp.user.params.query.UserAccountQuery;
import com.zxp.user.params.query.UserBaseQuery;
import com.zxp.user.params.query.UserIdentifyQuery;
import com.zxp.user.params.update.UserSecurityParams;
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
    public Optional<UserDTO> findUserDTOById(UserIdentifyQuery userIdentifyQuery) {
        return userRepository.findUserDTOByOpenID(userIdentifyQuery.getOpenID());
    }

    @Override
    public Optional<UserSecurity> findUserSecurityDTOById(UserIdentifyQuery userIdentifyQuery) {
        return userRepository.findUserSecurityByOpenID(userIdentifyQuery.getOpenID());
    }

    @Override
    public Optional<UserSecurity> findUserSecurityDTOByUserAccount(UserAccountQuery userAccountQuery) {
        return Optional.empty();
    }

    @Override
    public Boolean existsById(UserIdentifyQuery userIdentifyQuery) {
        return userRepository.existsById(userIdentifyQuery.getOpenID());
    }

    @Override
    public Boolean existsByQuery(UserBaseQuery userBaseQuery) {
        return null;
    }


    @Override
    public Boolean registerUser(UserSecurityParams userRegisterParams) {
        UserDO userDO = new UserDO();
        userDO.setOpenID(UUID.randomUUID().toString());
        BeanUtils.copyProperties(userRegisterParams, userDO);
        userRepository.save(userDO);
        return true;
    }

    @Override
    public Boolean updateUser(UserIdentifyQuery userIdentifyQuery, UserSecurityParams userRegisterParams) {
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
