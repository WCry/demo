package automicclass;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author zhangxuepei
 * @since 3.0
 */
public class TestLongAdder
{
    static  LongAdder counter =new LongAdder();
    private static ExecutorService executorService = Executors.newFixedThreadPool(5);
    public static void main(String[] args) throws InterruptedException {
        for (int i = 1; i <= 100; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    counter.add(2);
                }
            });
        }
        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.MINUTES);
        System.out.println(counter.sum());
        System.out.println(counter);
    }
}