package com.example.jpa.customer.repository;


import com.example.jpa.customer.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

//@Transactional(transactionManager = "customerTransactionManager",
//        rollbackFor = Exception.class,propagation = Propagation.NEVER)
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
