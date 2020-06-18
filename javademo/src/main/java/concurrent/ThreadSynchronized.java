package concurrent;

/**
 * 线程等待测试
 **/
public class ThreadSynchronized {
    private static Object myLock1 = new Object();
    private static Object myLock2 = new Object();
    public static void main(String[] args) {
        synchronized (myLock1){
            synchronized (myLock2){
                System.out.println("重入完成");
            }
        }
    }
}
