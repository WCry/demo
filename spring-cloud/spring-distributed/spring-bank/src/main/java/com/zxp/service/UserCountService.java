package com.zxp.service;

import com.zxp.dto.UserConsume;
import com.zxp.entity.po.UserCount;

import java.util.List;

public interface UserCountService {
    Boolean consumer(UserConsume userConsume);
    Boolean addMoney(UserConsume userConsume);
}
