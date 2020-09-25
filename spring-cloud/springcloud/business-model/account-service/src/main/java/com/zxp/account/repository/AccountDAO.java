package com.zxp.account.repository;

import com.zxp.account.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Optional;


public interface AccountDAO extends JpaRepository<Account, Long> {

    Optional<Account> findByUserId(String userId);
    @Transactional
    @Query("update Account t set t.money = t.money - ?2 where t.money - ?2 >0 and t.userId =?1")
    @Modifying
    int debitMoney(String userId, BigDecimal money);

    //默认主动加入当前已经存在的事务REQUIRED
    @Transactional(value = Transactional.TxType.REQUIRED)
    @Query("update Account t set t.money = t.money + ?2 where t.userId =?1")
    //clearAutomatically 在更新完成之后 自动清除对象缓存
    //jpa在查询对象的过程中使用缓存，所以需要让jpa知道更新数据，下次重新查询
    @Modifying(clearAutomatically = true)
    int rechargeMoney(String userId, BigDecimal money);
}
