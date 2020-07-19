package io.seata.sample.service;

import io.seata.sample.entity.Account;
import io.seata.sample.repository.AccountDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * Description：
 *
 * @author fangliangsheng
 * @date 2019-04-05
 */
@Service
public class AccountService {

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
        Account account = accountDAO.findByUserId(userId);
        account.setMoney(account.getMoney().subtract(num));
        accountDAO.save(account);
        if (ERROR_USER_ID.equals(userId)) {
            throw new RuntimeException("account branch exception");
        }
    }
}
