package concurrent;

/**
 * Description:利用线程join 需要等待join线程执行完成
 **/
public class ThreadJoin {
    public static void main(String[] args) {
        final Thread threadSalesperson = new Thread(new Runnable() {
            public void run() {
                System.out.println("有客户了");
            }
        }, "Sales Persion");
        final Thread threadProductManager = new Thread(new Runnable() {
            public void run() {
                try {
                    System.out.println("产品经理准备好了");
                    threadSalesperson.join();
                    System.out.println("产品经理规划需求！");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Product Manager");
        final Thread threadProgrammer = new Thread(new Runnable() {
            public void run() {
                try {
                    System.out.println("程序员准备好了");
                    threadProductManager.join();
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
