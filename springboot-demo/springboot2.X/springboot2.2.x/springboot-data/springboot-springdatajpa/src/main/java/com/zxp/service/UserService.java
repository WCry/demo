package com.zxp.service;

import com.zxp.dao.LockUserJpaRepository;
import com.zxp.entity.po.UserStudent;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {
    private final  LockUserJpaRepository lockUserJpaRepository;

    public UserService(LockUserJpaRepository lockUserJpaRepository) {
        this.lockUserJpaRepository = lockUserJpaRepository;
    }
    @Transactional(rollbackFor = Exception.class)
    public String findUser() {
        Optional<UserStudent> user=lockUserJpaRepository.findById(1L);
        return "dsd";
    }

    public String findUserSleep() throws InterruptedException {
        Optional<UserStudent> user=lockUserJpaRepository.findById(1L);
        Thread.sleep(10000);
        return "dsd";
    }
    @Transactional(rollbackFor = Exception.class)
    public String updateUserSleep() throws InterruptedException {
        lockUserJpaRepository.updateStateByMoney(1L, 28);
        Thread.sleep(10000);
        return "dsd";
    }
    @Transactional(rollbackFor = Exception.class)
    public String updateUser() {
        lockUserJpaRepository.updateStateByMoney(1L, 38);
        //Optional<User> userList = lockUserJpaRepository.findById(1L);
        return "dssssssd";
    }
}
