package threadpool.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author zhangxuepei
 * @since 3.0
 * 一个CompletableFuture 后面可以跟随多个下一步处理方式
 * 可以实现一个任务结果的扇出并发执行
 * future.handle
 */
public class MultiHandleException {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> 2);
        //可以进行数据流的扇出做多种处理方式
        future1.handleAsync((s,t)->{
            for(int i=0;i<10;i++){
                System.out.println("处理1");
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return "d";
        });
        future1.handleAsync((s,t)->{
            for(int i=0;i<10;i++){
                System.out.println("处理2");
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return "d";
        });
        //获取正在等待完成的任务
        System.out.println(future1.get());
        Thread.sleep(1000);
    }
}
