package Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.BiConsumer;

/**
 * 用户有多种支付工具（余额、优惠券、银行卡等），假如每种支付方式通过调用远程服务获取可用性。
 */
public class PayToolServiceContext implements PayToolService{
    private List<PayToolService> payToolServices;
    @Override
    public boolean avaliable() {
        return true;
    }

    @Override
    public PayToolEnum getPayTool() throws ExecutionException, InterruptedException {
        List<CompletableFuture<PayToolEnum>> completableFutures=new ArrayList<>();
        for (PayToolService payToolService : payToolServices) {
            if(payToolService.avaliable()){
                completableFutures.add(CompletableFuture.supplyAsync(()->{
                    try {
                        return payToolService.getPayTool();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return null;
                }));
            }
        }
        CompletableFuture<Object> dsd=
                CompletableFuture.
                        anyOf(completableFutures.toArray(new CompletableFuture[0]));
        return  (PayToolEnum)dsd.get();
//        while (true){
//            for (CompletableFuture<PayToolEnum> completableFuture : completableFutures) {
//                if(completableFuture.isDone()){
//                    return completableFuture.get();
//                }
//            }
//        }
    }
}
