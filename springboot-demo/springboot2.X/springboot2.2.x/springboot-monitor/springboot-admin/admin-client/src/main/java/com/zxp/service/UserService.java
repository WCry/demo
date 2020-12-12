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
    public String findUser() {
        Optional<User> user = lockUserJpaRepository.findById(1L);
        return user.get().getName();
    }

    @Transactional(rollbackFor = Exception.class)
    public String updateUser() {
        lockUserJpaRepository.updateStateByMoney(1L, 38);
        return "更新成功";
    }
}
