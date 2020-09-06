package com.zxp.goods.service;

import com.zxp.goods.params.GoodsPreOccupationDTO;
import com.zxp.goods.respository.GoodsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class GoodsServiceImpl implements GoodsService {

    private GoodsRepository goodsRepository;
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean goodsPreOccupation(GoodsPreOccupationDTO goodsPreOccupationDTO) {
        return null;
    }
}
