package concurrent.lock;

import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Author: zhangxuepei
 * Date: 2020/3/16 19:08
 * Content:
 *    测试读写锁互斥锁
 *    特点：支持重入
 *          写锁是互斥的
 *          读锁是共享
 *          写锁可以降级为读锁，读锁不可以升级为写锁
 */
public class TestReentrantWriteLock {
    public static void main(String[] args) throws InterruptedException {
        TestReentrantWriteLock testReentrantWriteLock=new TestReentrantWriteLock();
        Thread thread=new Thread(() -> {
            testReentrantWriteLock.put("test","2222");
            System.out.println("线程1获取："+testReentrantWriteLock.get("test"));
        });
        thread.start();
        Thread threadGet=new Thread(() -> {
            System.out.println("线程2获取："+testReentrantWriteLock.get("test"));
        });
        threadGet.start();
        Thread.sleep(1000);
    }
    private final Map<String, String> m = new TreeMap<String, String>();
    private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    private final Lock r = rwl.readLock();
    private final Lock w = rwl.writeLock();

    public String get(String key) {
        r.lock();
        try {
            return m.get(key);
        } finally {
            r.unlock();
        }
    }

    public String[] allKeys() {
        r.lock();
        try {
            return (String[]) m.keySet().toArray();
        } finally {
            r.unlock();
        }
    }

    public String put(String key, String value) {
        w.lock();
        try {
            return m.put(key, value);
        } finally {
            w.unlock();
        }
    }

    public void clear() {
        w.lock();
        try {
            m.clear();
        } finally {
            w.unlock();
        }
    }
}
