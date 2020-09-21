package com.zxp.account.repository;

import com.zxp.account.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;


public interface AccountDAO extends JpaRepository<Account, Long> {

    Account findByUserId(String userId);

    @Query("update Account t set t.money = t.money - ?2 where t.money - ?2 >0 and t.userId =?1")
    @Modifying
    Account debitMoney(String userId, BigDecimal money);
    @Query("update Account t set t.money = t.money + ?2 where t.userId =?1")
    @Modifying
    Account rechargeMoney(String userId,BigDecimal money);
}
