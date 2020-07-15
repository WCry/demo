package threadpool.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author zhangxuepei
 * @since 3.0
 */
public class HandleException {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        String name = null;
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            if (name == null) {
                throw new RuntimeException("Computation error!");
            }
            return "Hello, " + name;
        }).handle((s, t) -> {
            //handle 异常处理
            System.out.println(t.getMessage());
            return s != null ? s : "Hello, Stranger!";
        });
        //获取正在等待完成的任务
         //future.getNumberOfDependents()
        System.out.println(future.get()); // Hello, Stranger!
    }
}
