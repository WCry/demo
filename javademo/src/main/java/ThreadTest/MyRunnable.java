package ThreadTest;

/**
 * @author zhangxuepei
 * @since 3.0
 */
class MyRunnable implements Runnable {

    @Override
    public void run() {
        synchronized(this) {
            try {
                for (int i = 0; i < 5; i++) {
                    Thread.sleep(1000); // 休眠100ms
                    System.out.println(Thread.currentThread().getName() + " loop " + i);
                }
            } catch (InterruptedException ie) {
            }
        }
    }
}
