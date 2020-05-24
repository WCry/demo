package concurrent;

import sun.awt.Mutex;

import java.util.concurrent.CountDownLatch;

/**
 * user:zxp
 * Day:2020,02,14
 *  计数器线程锁测试
 **/
public class CountDownLatchThread {
    public static void main(String[] args) {
        CountDownLatch countDownLatchManager=new CountDownLatch(1);
        CountDownLatch countDownLatchProgramer=new CountDownLatch(1);
        final Thread threadSalesperson = new Thread(new Runnable() {
            public void run() {
                System.out.println("有客户了");
                countDownLatchManager.countDown();
            }
        }, "Sales Persion");
        final Thread threadProductManager = new Thread(new Runnable() {
            public void run() {
                try {
                    countDownLatchManager.await();
                    System.out.println("产品经理规划需求！");
                    countDownLatchProgramer.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Product Manager");
        final Thread threadProgrammer = new Thread(new Runnable() {
            public void run() {
                try {
                    countDownLatchProgramer.await();
                    System.out.println("程序员开始器敲代码!");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Programemr");
        threadProgrammer.start();
        threadProductManager.start();
        threadSalesperson.start();
    }
}
