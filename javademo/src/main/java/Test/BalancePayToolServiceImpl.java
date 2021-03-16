package Test;

public class BalancePayToolServiceImpl implements PayToolService {
    @Override
    public boolean avaliable() {
        return true;
    }

    @Override
    public PayToolEnum getPayTool() {
        //远程过程调用
        return PayToolEnum.BALANCE;
    }
}
