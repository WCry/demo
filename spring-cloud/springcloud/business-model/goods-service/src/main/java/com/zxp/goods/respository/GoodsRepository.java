package com.zxp.goods.respository;


import com.zxp.goods.pojo.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import javax.persistence.LockModeType;


public interface GoodsRepository extends JpaRepository<Goods, Long> {

    Goods findGoodsById(String goodsID);
}
