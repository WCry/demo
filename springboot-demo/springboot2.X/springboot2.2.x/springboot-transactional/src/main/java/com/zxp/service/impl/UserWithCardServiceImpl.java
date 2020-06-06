package com.zxp.service.impl;

import com.zxp.entity.po.User;
import com.zxp.entity.po.UserCard;
import com.zxp.service.UserCardService;
import com.zxp.service.UserService;
import com.zxp.service.UserWithCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

/**
 * @author zhangxuepei
 * @since 3.0
 */
@Service
public class UserWithCardServiceImpl implements UserWithCardService {
    @Autowired
    private UserService userService;
    @Autowired
    private UserCardService userCardService;

    @Override
    //事务生效
    @Transactional(rollbackOn = {RuntimeException.class, Error.class})
    public void addUser(User user, int cardCount) {
        UUID uuid = UUID.randomUUID();
        user.setUserID(uuid.toString());
        userService.addUser(user);
        UserCard userCard = new UserCard();
        userCard.setCount(cardCount);
        userCard.setUserID(uuid.toString());
        userCardService.addUserCard(userCard);
    }
}
