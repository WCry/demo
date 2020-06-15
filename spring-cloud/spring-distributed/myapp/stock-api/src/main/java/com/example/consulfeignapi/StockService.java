package com.example.consulfeignapi;

import com.example.consulfeignapi.dto.StockDto;
import com.example.consulfeignapi.util.Gender;
import com.example.consulfeignapi.util.Language;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


public interface StockService {
    /**
     * 库存减少
     * @param stockDto
     * @return
     */
    @PostMapping("/subtract")
    Boolean stockSubtract(StockDto stockDto);
}
