package concurrent.lock;

import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Author: zhangxuepei
 * Date: 2020/3/16 19:08
 * Content:
 *    测试锁互斥锁
 */
public class TestReentrantLock {
    public static void main(String[] args) throws InterruptedException {
        TestReentrantLock testReentrantWriteLock=new TestReentrantLock();
        Thread thread=new Thread(() -> {
            testReentrantWriteLock.put("test","2222");
            System.out.println("线程1获取："+testReentrantWriteLock.get("test"));
        });
        thread.start();
        Thread threadGet=new Thread(() -> {
            System.out.println(testReentrantWriteLock.allKeys().length);
            System.out.println("线程2获取："+testReentrantWriteLock.get("test"));
        });
        threadGet.start();
        Thread.sleep(1000);
    }
    private final Map<String, String> m = new TreeMap<String, String>();
    private final ReentrantLock rwl = new ReentrantLock();

    public String get(String key) {
        rwl.lock();
        try {
            try {
                System.out.println(key);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return m.get(key);
        } finally {
            rwl.unlock();
        }
    }

    public String[] allKeys() {
        rwl.lock();
        try {
            System.out.println("获取全部");
            String[] returnd=new String[m.size()];
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("获取全部结束");
            return  m.keySet().toArray(returnd);
        } finally {
            rwl.unlock();
        }
    }

    public String put(String key, String value) {
        rwl.lock();
        try {
            return m.put(key, value);
        } finally {
            rwl.unlock();
        }
    }

    public void clear() {
        rwl.lock();
        try {
            m.clear();
        } finally {
            rwl.unlock();
        }
    }
}
