package concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Content:
 * condition翻译状态，状况。
 *          实现状态的 await()/single();实现状态的等待和通知
 * 和Object的await()/notify()区别
 * Object的wait和notify/notify是与对象监视器配合完成线程间的等待/通知机制，
 * 而Condition与Lock配合完成等待通知机制，
 * 前者是java底层级别的，后者是语言级别的，
 * 具有更高的可控制性和扩展性。两者除了在使用方式上不同外，
 * 在功能特性上还是有很多的不同：
 * 1.Condition能够支持不响应中断，而通过使用Object方式不支持；
 * 2.Condition能够支持多个等待队列（new 多个Condition对象），而Object方式只能支持一个；
 * 3.Condition能够支持超时时间的设置，而Object不支持
 */
public class TestCondition {
    private static ReentrantLock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();
    //volatile 实现变量的变化线程可见
    private static volatile boolean flag = false;

    public static void main(String[] args) {
        Thread waiter = new Thread(new waiter());
        waiter.start();
        Thread signaler = new Thread(new signaler());
        signaler.start();
    }

    static class waiter implements Runnable {
        @Override
        public void run() {
            lock.lock();
            try {
                while (!flag) {
                    System.out.println(Thread.currentThread().getName() + "当前条件不满足等待");
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName() + "接收到通知条件满足");
            } finally {
                lock.unlock();
            }
        }
    }
    static class signaler implements Runnable {
        @Override
        public void run() {
            lock.lock();
            try {
                flag = true;
                condition.signalAll();
            } finally {
                lock.unlock();
            }
        }
    }
}
