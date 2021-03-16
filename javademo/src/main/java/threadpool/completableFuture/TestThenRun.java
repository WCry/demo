package threadpool.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author zhangxuepei
 * @since 3.0
 * 执行任务 等待结束的时候运行一个Runnable的对象
 * future.thenRun
 * get方法和join方法一样  join不抛出异常，避免方法臃肿
 * get方法需要显示的抛出异常
 */
public class TestThenRun {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
                try {
                    System.out.println("任务1开始执行");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            return "任务执行结束!";
        });
        //执行完成之后执行下一个任务
        future.thenRun(() -> System.out.println("执行下一个任务"));
        //get方式是阻塞的 只有future所有任务都执行完成之后 get方法才能够返回
        System.out.println(future.get());
        System.out.println("执行结束");
        // Hello, Stranger!
    }
}
