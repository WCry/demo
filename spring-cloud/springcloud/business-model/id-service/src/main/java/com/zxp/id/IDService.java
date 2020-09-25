package com.zxp.id;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class IDService {

    @Transactional
    public void create(String userId, String commodityCode, Integer count) {

    }

}
