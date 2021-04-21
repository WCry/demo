package com.example.jpa.product.service;

import com.example.jpa.product.models.Product;
import com.example.jpa.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductTXService {
    private final ProductRepository productRepository;

    public void save(Product product) {
        productRepository.save(product);
    }
}
