package com.zxp.account.service;

import com.zxp.account.entity.Account;
import com.zxp.account.pojo.AccountParams;
import com.zxp.account.pojo.ChangeParams;
import com.zxp.account.pojo.TransferParams;
import com.zxp.account.repository.AccountDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;


@Service
public class AccountService  {

    private static final String ERROR_USER_ID = "1002";
    private final AccountDAO accountDAO;

    @Autowired
    public AccountService(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    /**
     * 开启本地事务
     * @param userId
     * @param num
     */
    @Transactional(rollbackFor = Exception.class)
    public void debit(String userId, BigDecimal num) {
        Optional<Account> accountOptional = accountDAO.findByUserId(userId);
        Account account=accountOptional.get();
        account.setMoney(account.getMoney().subtract(num));
        accountDAO.save(account);
        if (ERROR_USER_ID.equals(userId)) {
            throw new RuntimeException("account branch exception");
        }
    }


    public Boolean debitAccount(ChangeParams changeParams) {
        return null;
    }


    public Boolean existAccount(AccountParams accountParams) {
        return null;
    }


    public Boolean accountRecharge(ChangeParams changeParams) {
        return null;
    }


    public Boolean accountTransfer(TransferParams transferParams) {
        return null;
    }


    public Boolean accountFreeze(ChangeParams changeParams) {
        return null;
    }


    public Boolean accountUnFreeze(ChangeParams changeParams) {
        return null;
    }
}
