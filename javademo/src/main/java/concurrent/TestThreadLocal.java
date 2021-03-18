package concurrent;

/**
 * @author zhangxuepei
 * @since 3.0
 */
public class TestThreadLocal {
    public static void main(String[] args) throws InterruptedException {
        A a = new A();
        Thread das = new Thread(() -> a.soud("测试.dsad"));
        Thread das2 = new Thread(() -> a.soud("dsad22"));
        das.start();
        das2.start();
        a.GetA();
        Thread.sleep(100022);
    }

    static class A {
        ThreadLocal<Integer> threadLocal = new ThreadLocal();
        
        public void soud(String dsa) {
            if (dsa.equals("测试.dsad")) {
                threadLocal.set(10);
            }
            System.out.println(threadLocal.get());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public void GetA() {
            System.out.println("dasd");
        }
    }

}
