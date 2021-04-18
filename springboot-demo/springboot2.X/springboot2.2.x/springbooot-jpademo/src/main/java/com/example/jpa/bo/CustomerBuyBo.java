package com.example.jpa.bo;

import com.example.jpa.customer.models.Customer;
import com.example.jpa.product.models.Product;
import lombok.Data;

@Data
public class CustomerBuyBo {
    private Customer customer;
    private Product product;
}
