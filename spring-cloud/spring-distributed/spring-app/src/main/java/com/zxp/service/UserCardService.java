package com.zxp.service;

import com.zxp.entity.po.UserCard;

/**
 * @author zhangxuepei
 * @since 3.0
 */
public interface UserCardService {
    //初始化信用卡
    void addUserCard(UserCard userCard);
    //信用卡消费 更新
    void updateUserCard(UserCard userCard);
}
