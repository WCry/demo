package com.example.jpa.service;

import com.example.jpa.bo.CustomerBuyBo;
import com.example.jpa.customer.service.CustomerTXService;
import com.example.jpa.product.service.ProductTXService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class CustomerBuyProduct {
    private final CustomerTXService customerTXService;
    private final ProductTXService productTXService;
    @Transactional(transactionManager = "jtaTransactionManager")
    public void customerBuyProduct(CustomerBuyBo customerBuyBo){
        productTXService.save(customerBuyBo.getProduct());
        customerTXService.save(customerBuyBo.getCustomer());
    }
}
