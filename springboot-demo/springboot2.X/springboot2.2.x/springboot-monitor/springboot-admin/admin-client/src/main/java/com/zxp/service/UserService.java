package com.zxp.service;

import com.zxp.dao.LockUserJpaRepository;
import com.zxp.po.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {
    private final LockUserJpaRepository lockUserJpaRepository;

    public UserService(LockUserJpaRepository lockUserJpaRepository) {
        this.lockUserJpaRepository = lockUserJpaRepository;
    }

    @Transactional(rollbackFor = Exception.class)
    public String findUser(Long userID) {
        Optional<User> user = lockUserJpaRepository.findById(userID);
        return user.get().getName();
    }

    @Transactional(rollbackFor = Exception.class)
    public String updateUser(Long userID,Integer age) {
        lockUserJpaRepository.updateAgeByID(userID, age);
        return "更新成功";
    }
}
