package ThreadTest;

public class MainAndSubThreadDemo {

    public static void main(String[] args) {
        /**
         * JAVA 的线程可以分为用户线程和守护线程
         * 用户线程和主线程之间相互隔离线程
         * 守护线程在用户线程结束之后会自动的结束
         * Thread 默认启动的是用户线程，所以主线程结束，不会影响用户线程的执行
         * t2.setDaemon() 标识用户是否是守护线程
         */
        Runnable demo = new MyRunnable();
        Thread t1 = new Thread(demo, "t1");
        Thread t2 = new Thread(demo, "t2");

        t1.start();
        t2.start();
    }
}
