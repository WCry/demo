package com.zxp.account.repository;

import com.zxp.account.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AccountDAO extends JpaRepository<Account, Long> {

    Account findByUserId(String userId);
}
