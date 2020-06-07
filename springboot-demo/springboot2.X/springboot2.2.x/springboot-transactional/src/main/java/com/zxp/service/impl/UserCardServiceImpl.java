package com.zxp.service.impl;

import com.zxp.dao.UserCardRepository;
import com.zxp.entity.po.UserCard;
import com.zxp.service.UserCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhangxuepei
 * @since 3.0
 */
@Service
public class UserCardServiceImpl implements UserCardService {
    @Autowired
    private UserCardRepository userCardRepository;

    @Override
    public void addUserCard(UserCard userCard) {
        if(userCard.getCount()>100){
            //todo:这样的写法仅仅是为了试验 事务回滚
            throw new RuntimeException("初始化金额不能大于100！");
        }
        userCardRepository.save(userCard);
    }

    @Override
    public void updateUserCard(UserCard userCard) {
        userCardRepository.save(userCard);
    }
}
