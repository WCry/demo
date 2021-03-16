package threadpool.completableFuture;

import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author zhangxuepei
 * @since 3.0
 *  Either代表任意一个任务完成都可以继续执行下一个函数
 */
public class TestEither {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        //获取特定算法的安全随机数  SecureRandom.getInstance()
        //随机算法提供者和算法名称获取随机生成器等，比Random安全
      //  SecureRandom secureRandom=SecureRandom.getInstanceStrong();
        Random random = new Random();
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "from future1";
        });

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(random.nextInt(100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "from future2";
        });
        //Either代表任意一个任务完成都可以继续执行下一个函数
        CompletableFuture<String> future = future1.
                applyToEither(future2, str -> "The future is " + str);
        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
