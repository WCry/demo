package threadpool.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author zhangxuepei
 * @since 3.0
 * 多个任务 任一一个任务最先完成 返回任务执行结果
 * CompletableFuture.anyOf
 * 底层采用的是ForkJoinThreadPool的线程池执行
 * 底层根据运行机器的CPU情况,进行多线程操作
 */
public class TestAnyOf {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //任务1 计算
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            int number = 0;
            try {
                 number=ThreadLocalRandom.current().nextInt(1000);
                 System.out.println("任务开始执行1："+number);
                Thread.sleep(number);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "任务1执行完成, " + number;
        });
        CompletableFuture<String> future2= CompletableFuture.supplyAsync(() -> {
            int number = 0;
            try {
                number=ThreadLocalRandom.current().nextInt(1000);
                System.out.println("任务开始执行2："+number);
                Thread.sleep(number);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "任务2执行完成, " + number;
        });
        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> {
            int number = 0;
            try {
                number=ThreadLocalRandom.current().nextInt(1000);
                System.out.println("任务开始执行3："+number);
                Thread.sleep(number);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "任务3执行完成, " + number;
        });
        //那个任务最先完成，然后进行返回，得到执行结果
        String result=(String) CompletableFuture.anyOf(future1,future2,future3).get();
        System.out.println(result);
    }
}
