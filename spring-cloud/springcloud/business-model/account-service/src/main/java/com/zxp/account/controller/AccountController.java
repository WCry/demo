package com.zxp.account.controller;

import com.zxp.account.service.AccountService;
import com.zxp.user.api.feign.AccountFeignAPI;
import com.zxp.user.api.pojo.AccountParams;
import com.zxp.user.api.pojo.ChangeParams;
import com.zxp.user.api.pojo.TransferParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;


@RestController
public class AccountController implements AccountFeignAPI {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @RequestMapping("/debit")
    public Boolean debit(String userId, BigDecimal money) {
        accountService.debit(userId, money);
        return true;
    }

    @Override
    public Boolean debitAccount(ChangeParams changeParams) {
        return null;
    }

    @Override
    public Boolean existAccount(AccountParams accountParams) {
        return null;
    }

    @Override
    public Boolean accountRecharge(ChangeParams changeParams) {
        return null;
    }

    @Override
    public Boolean accountTransfer(TransferParams transferParams) {
        return null;
    }

    @Override
    public Boolean accountFreeze(ChangeParams changeParams) {
        return null;
    }

    @Override
    public Boolean accountUnFreeze(ChangeParams changeParams) {
        return null;
    }
}
