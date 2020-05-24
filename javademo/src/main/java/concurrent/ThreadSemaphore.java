package concurrent;

import java.util.concurrent.Semaphore;

/**
 * 信号量   测试
 **/
public class ThreadSemaphore {
    public static void main(String[] args) {
        //默认应该是零个信号量
        Semaphore semaphore=new Semaphore(1);
        Semaphore semaphore1=new Semaphore(0);
        final Thread threadSalesperson = new Thread(() -> {
            System.out.println("有客户了");
            semaphore.release();
        }, "Sales Persion");
        final Thread threadProductManager = new Thread(() -> {
            try {
                System.out.println("产品经理准备好了");
                semaphore.acquire();
              //  semaphore.acquire();
               // semaphore.acquire();
                //有参数 可以是获取许可数量
                semaphore.acquire();
                System.out.println("产品经理规划需求！");
                semaphore1.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Product Manager");
        final Thread threadProgrammer = new Thread(() -> {
            try {
                System.out.println("程序员准备好了");
                semaphore1.acquire();
                System.out.println("程序员开始器敲代码!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Programmer");
        threadProgrammer.start();
        threadProductManager.start();
        threadSalesperson.start();
    }
}
