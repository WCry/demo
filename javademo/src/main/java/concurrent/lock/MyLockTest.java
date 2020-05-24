package concurrent.lock;

/**
 * user:zxp
 * Day:2020,04,25
 **/
import java.util.concurrent.*;

/**
 * 自定义锁测试
 *
 * @author liqiang
 * @time 2019/11/29 12:39
 */
public class MyLockTest {

    public static void main(String[] args) throws InterruptedException {
        int threadNum = 10;
        int countPerThread = 10000;
        // 线程池创建的正确姿势
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(threadNum, threadNum, 1000, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10), new ThreadPoolExecutor.AbortPolicy());
        CountDownLatch countDownLatch = new CountDownLatch(threadNum);
        Counter counter = new Counter();
        Counter counterUnsafe = new Counter();

        for (int i = 0; i < threadNum; i++) {
            threadPool.submit(() -> {
                for (int j = 0; j < countPerThread; j++) {
                    counter.getAndIncrement();
                    counterUnsafe.getAndIncrementUnSfae();
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        System.out.printf("%s 个线程，每个线程累加了 %s 次，执行结果：safeCounter = %s, unsafeCounter = %s ", threadNum, countPerThread, counter.get(), counterUnsafe.get());
        threadPool.shutdownNow();
    }

}

class Counter {
    private MutexLock mutexLock;
    private volatile int count;

    Counter() {
        this.mutexLock = new MutexLock();
    }

    int get() {
        return count;
    }

    int getAndIncrement() {
        mutexLock.lock();
        count++;
        mutexLock.unlock();
        return count;
    }

    int getAndIncrementUnSfae() {
        count++;
        return count;
    }
}
