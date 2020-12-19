package com.zxp.demo.repository;

import com.zxp.demo.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountDAO extends JpaRepository<Account, Long> {

    Account findByUserId(String userId);

}
