package com.example.stock;

import com.example.consulfeignapi.StockService;
import com.example.consulfeignapi.dto.StockDto;
import com.example.consulfeignapi.util.Gender;
import com.example.consulfeignapi.util.Language;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangxuepei
 * @since 3.0
 */
@RestController
public class StockImpl implements StockService {

    @Override
    public Boolean stockSubtract(StockDto stockDto) {
        return null;
    }
}
