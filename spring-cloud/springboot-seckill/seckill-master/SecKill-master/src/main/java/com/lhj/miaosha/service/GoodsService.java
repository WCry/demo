package com.lhj.miaosha.service;


import com.lhj.miaosha.dao.GoodsDao;
import com.lhj.miaosha.domain.MiaoshaGoods;
import com.lhj.miaosha.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class GoodsService {
    @Autowired
    GoodsDao goodsDao;
    public List<GoodsVo> listGoodsVo() {
        return goodsDao.listGoodsVo();
    }


    public GoodsVo getGoodsVoByGoodsId(long goodsId) {
        Integer integer=new Integer(String.valueOf(goodsId));
        return goodsDao.getGoodsVoByGoodsId(integer);
    }

    public boolean reduceStock(GoodsVo goods) {
        MiaoshaGoods g = new MiaoshaGoods();
        g.setGoodsId(goods.getId());
        return goodsDao.reduceStock(g) > 0 ;
    }
}
