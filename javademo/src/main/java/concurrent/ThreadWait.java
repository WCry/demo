package concurrent;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 线程等待测试
 **/
public class ThreadWait {
    private static Object myLock1 = new Object();
    private static Object myLock2 = new Object();
    private static Boolean t1Run = false;
    private static Boolean t2Run = false;

    public static void main(String[] args) {

        final Thread threadSalesperson = new Thread(new Runnable() {
            public void run() {
                synchronized (myLock1) {
                    System.out.println("有客户了");
                    myLock1.notify();
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    t1Run = true;
                }
            }
        }, "Sales Persion");
        final Thread threadProductManager = new Thread(new Runnable() {
            public void run() {
                try {
                    synchronized (myLock1) {
                        if (!t1Run) {
                            myLock1.wait();
                        }
                    }
                    synchronized (myLock2) {
                        System.out.println("产品经理规划需求！");
                        myLock2.notify();
                        t2Run = true;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Product Manager");
        final Thread threadProgrammer = new Thread(new Runnable() {
            public void run() {
                try {
                    synchronized (myLock2) {
                        if (!t2Run) {
                            myLock2.wait();
                        }
                    }
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
