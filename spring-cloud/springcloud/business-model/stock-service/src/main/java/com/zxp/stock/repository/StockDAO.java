package com.zxp.stock.repository;

import com.zxp.stock.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StockDAO extends JpaRepository<Stock, String> {

    Stock findByCommodityCode(String commodityCode);

}
