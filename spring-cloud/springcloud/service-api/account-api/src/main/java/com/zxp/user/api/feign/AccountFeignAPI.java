package com.zxp.user.api.feign;


import com.zxp.user.api.pojo.AccountParams;
import com.zxp.user.api.pojo.ChangeParams;
import com.zxp.user.api.pojo.TransferParams;
import org.springframework.web.bind.annotation.PostMapping;


/**
 * 账户交易API
 */
public interface AccountFeignAPI {
    /**
     * 账户扣减API
     * @param changeParams
     * @return
     */
    @PostMapping("/debit")
    Boolean debitAccount(ChangeParams changeParams);

    /**
     * 账户是否存在检查
     * @param accountParams
     * @return
     */
    @PostMapping("/exist")
    Boolean existAccount(AccountParams accountParams);

    /**
     *账户充值
     * @param changeParams
     * @return
     */
    @PostMapping("/recharge")
    Boolean accountRecharge(ChangeParams changeParams);

    /**
     * 账户之间进行转账
     * @param transferParams
     * @return
     */
    @PostMapping("/transfer")
    Boolean accountTransfer(TransferParams transferParams);
    /**
     * 对于账户进行冻结部分资金
     * @param changeParams
     * @return
     */
    @PostMapping("/freeze")
    Boolean accountFreeze(ChangeParams changeParams);

    /**
     * 对于资金进行解冻
     * @param changeParams
     * @return
     */
    @PostMapping("/unFreeze")
    Boolean accountUnFreeze(ChangeParams changeParams);
}
