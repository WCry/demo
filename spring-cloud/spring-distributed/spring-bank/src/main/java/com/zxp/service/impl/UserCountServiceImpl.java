package com.zxp.service.impl;

import com.zxp.dao.UserCountRepository;
import com.zxp.dto.UserConsume;
import com.zxp.entity.po.UserCount;
import com.zxp.service.UserCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class UserCountServiceImpl implements UserCountService {

    private final UserCountRepository userCountRepository;
    @Autowired
    public UserCountServiceImpl(UserCountRepository userCountRepository) {
        this.userCountRepository = userCountRepository;
    }

    @Transactional(isolation = Isolation.DEFAULT, rollbackFor = {RuntimeException.class, Error.class})
    @Override
    public Boolean consumer(UserConsume userConsume) {
        UserCount userCount= userCountRepository.findAllByUserID(userConsume.getUserID());
        BigDecimal balanceCount=userCount.getCount();
        BigDecimal surplus= balanceCount.subtract(userConsume.getCount());
        if(surplus.compareTo(BigDecimal.ZERO)<0){
            return false;
        }
        userCount.setCount(balanceCount);
        this.userCountRepository.save(userCount);
        return true;
    }
    @Transactional(isolation = Isolation.DEFAULT, rollbackFor = {RuntimeException.class, Error.class})
    @Override
    public Boolean addMoney(UserConsume userConsume) {
        UserCount userCount= userCountRepository.findAllByUserID(userConsume.getUserID());
        BigDecimal surplus= userCount.getCount().add(userConsume.getCount());
        userCount.setCount(surplus);
        userCountRepository.save(userCount);
        return null;
    }
}
