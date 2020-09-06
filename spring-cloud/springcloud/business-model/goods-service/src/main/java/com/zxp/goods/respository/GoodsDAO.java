package com.zxp.goods.respository;


import com.zxp.goods.pojo.Goods;
import org.springframework.data.jpa.repository.JpaRepository;



public interface GoodsDAO extends JpaRepository<Goods, Long> {
    Goods findGoodsById(String goodsID);
}
