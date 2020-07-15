package threadpool.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;

/**
 * @author zhangxuepei
 * @since 3.0
 * CompletableFuture的Result采用volatile方式实现线程可见
 *  https://www.cnblogs.com/dennyzhangdd/p/7010972.html
 */
public class TestCompletableFutureFirst {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //将来可完成的结果
        CompletableFuture<String> completableFuture = new CompletableFuture<>();
        //新生成一个线程执行任务
        new Thread(() -> {
            // 模拟执行耗时任务
            System.out.println("task doing...");
            try {
                Thread.sleep(3000);
                int i = 1 / 0;
            } catch (Exception e) {
                // 告诉completableFuture任务发生异常了
                completableFuture.completeExceptionally(e);
            }
            // 告诉completableFuture任务已经完成
            completableFuture.complete("ok");
        }).start();
        // 获取任务结果，如果没有完成会一直阻塞等待
        String result = completableFuture.get();
        System.out.println("计算结果:" + result);
    }

}
