package com.example.jpa.service;

import com.example.jpa.bo.CustomerBuyBo;
import com.example.jpa.customer.repository.CustomerRepository;
import com.example.jpa.product.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerBuyProduct {
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;

    public void customerBuyProduct(CustomerBuyBo customerBuyBo){
        customerRepository.save(customerBuyBo.getCustomer());
        productRepository.save(customerBuyBo.getProduct());
    }
}
