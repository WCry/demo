package concurrent;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhangxuepei
 * @since 3.0
 * 所有的锁都是基于操作系统的
 * 自旋锁和监视锁实现
 */
public class TestLock {

    private static Lock lock;

    public static void main(String[] args) throws InterruptedException {
        lock = new ReentrantLock();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                lock.lock();
                System.out.println("dasd");
                lock.unlock();
            }
        }, 5);
        System.out.println("dadasdassd");
        change();
        Thread.sleep(1000);
    }

    private static void change() throws InterruptedException {
        lock.lock();
        Thread.sleep(10000);
        System.out.println("dasdas");
        lock.unlock();
    }
}
