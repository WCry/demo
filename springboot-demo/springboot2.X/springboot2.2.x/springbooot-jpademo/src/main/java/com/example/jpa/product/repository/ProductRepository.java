package com.example.jpa.product.repository;

import com.example.jpa.product.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

//@Transactional(transactionManager = "productTransactionManager",
//        rollbackFor = Exception.class,propagation = Propagation.NEVER)

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
