package com.zxp.user.sericefeign.respository;


import com.zxp.user.sericefeign.pojo.Goods;
import org.springframework.data.jpa.repository.JpaRepository;



public interface GoodsDAO extends JpaRepository<Goods, Long> {
    Goods findByUserId(String userId);
}
