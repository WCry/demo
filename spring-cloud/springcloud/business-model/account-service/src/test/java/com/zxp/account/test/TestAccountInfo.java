package com.zxp.account.test;


import com.zxp.account.AccountApplication;
import com.zxp.account.repository.AccountDAO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest(classes = AccountApplication.class)
public class TestAccountInfo {
    @Autowired
    private AccountDAO accountDAO;

    @Test
    public void testDebitAccount() {
        System.out.println(accountDAO.debitMoney("1001", new BigDecimal("100")));
    }
}
