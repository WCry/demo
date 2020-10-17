package com.zxp.stock.service;


import com.zxp.stock.entity.Stock;
import com.zxp.stock.repository.StockDAO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class StockService {

    private final StockDAO stockDAO;

    public StockService(StockDAO stockDAO) {
        this.stockDAO = stockDAO;
    }

    @Transactional
    public void deduct(String commodityCode, int count) {
        Stock stock = stockDAO.findByCommodityCode(commodityCode);
        stock.setCount(stock.getCount() - count);
        stockDAO.save(stock);
    }
}
