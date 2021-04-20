package com.example.jpa.controller;

import com.example.jpa.bo.CustomerBuyBo;
import com.example.jpa.customer.models.Customer;
import com.example.jpa.product.models.Product;
import com.example.jpa.service.CustomerBuyProduct;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class BuyController {
    private CustomerBuyProduct customerBuyProduct;

    @GetMapping(value = "/buy")
    public void customerBuyProduct(@RequestParam String name) {
        CustomerBuyBo customerBuyBo = new CustomerBuyBo();
        Customer customer=new Customer(name);
        Product product=new Product("1123","月亮",12.2);
        customerBuyBo.setCustomer(customer);
        customerBuyBo.setProduct(product);
        customerBuyProduct.customerBuyProduct(customerBuyBo);
    }
}
