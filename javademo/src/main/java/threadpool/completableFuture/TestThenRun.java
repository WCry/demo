package threadpool.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author zhangxuepei
 * @since 3.0
 * get方法和Join方法一样  join不抛出异常，避免方法臃肿
 * get方法需要显示的抛出异常
 */
public class TestThenRun {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        String name = null;
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            if (name == null) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                throw new RuntimeException("Computation error!");
            }
            return "Hello, " + name;
        }).handle((s, t) -> {
            //handle 异常处理
            System.out.println(t.getMessage());
            return s != null ? s : "Hello, Stranger!";
        });
        //执行完成之后执行下一个任务
        future.thenRun(() -> System.out.println("执行下一个任务"));
        System.out.println("准备获取结果");
        //get方式是阻塞的 只有future所有任务都执行完成之后 get方法才能够返回
        System.out.println(future.get());
        System.out.println("执行结束");
        // Hello, Stranger!
    }
}
