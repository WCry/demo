package threadpool.threadlocal;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestThreadLocal {
    static ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        TestAbstractImpl testAbstractImpl = new TestAbstractImpl();
        TestAbstract testAbstract = testAbstractImpl;
        ExecutorService threadPool = Executors.newFixedThreadPool(20);
        threadPool.submit(() -> {
            testAbstractImpl.setInterValue(23);
            System.out.println(testAbstractImpl.getInterValue());
            testAbstract.setInterValue(24);
            System.out.println(testAbstractImpl.getInterValue());
            try {
                ObjectOutputStream oos = new ObjectOutputStream(new ByteArrayOutputStream());
                oos.writeObject(testAbstractImpl);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        threadPool.submit(() -> {
            System.out.println(testAbstract.getInterValue());
            testAbstractImpl.setInterValue(12);
            System.out.println(testAbstractImpl.getInterValue());
            System.out.printf("%-13.3s",threadLocal.get());
            System.out.println(threadLocal.get());
            if(threadLocal.get()==null){
                System.out.println("test");
                threadLocal.set(2);
            }
        });
        threadPool.shutdownNow();
    }
}
