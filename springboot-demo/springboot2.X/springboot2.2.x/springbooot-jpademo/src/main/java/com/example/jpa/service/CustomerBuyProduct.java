package com.example.jpa.service;

import com.example.jpa.bo.CustomerBuyBo;
import com.example.jpa.customer.repository.CustomerRepository;
import com.example.jpa.product.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.hibernate.boot.spi.InFlightMetadataCollector;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class CustomerBuyProduct {
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;
    @Transactional(transactionManager = "jtaTransactionManager")
    public void customerBuyProduct(CustomerBuyBo customerBuyBo){
        productRepository.save(customerBuyBo.getProduct());
        customerRepository.save(customerBuyBo.getCustomer());
    }
}
