package concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier:回环栅栏，需要等待所有线程都到齐，await()之后。
 * 同时继续往下走。
 **/
public class ThreadCyclicBarrier {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
        CyclicBarrier cyclicBarrier1 = new CyclicBarrier(2);
        final Thread threadSalesperson = new Thread(new Runnable() {
            public void run() {
                try {
                    System.out.println("有客户了");
                    cyclicBarrier.await();
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }, "Sales Persion");
        final Thread threadProductManager = new Thread(new Runnable() {
            public void run() {
                try {
                    System.out.println("产品经理准备好了");
                    cyclicBarrier.await();
                    System.out.println("有等待了");
                    cyclicBarrier.await();

                    System.out.println("产品经理规划需求！");
                    cyclicBarrier1.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }, "Product Manager");
        final Thread threadProgrammer = new Thread(new Runnable() {
            public void run() {
                try {
                    System.out.println("程序员准备好了");
                    cyclicBarrier1.await();
                    System.out.println("程序员开始器敲代码!");
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }, "Programemr");
        threadProgrammer.start();
        threadProductManager.start();
        threadSalesperson.start();
    }
}
