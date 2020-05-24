package demo.curator;

import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.apache.curator.framework.recipes.locks.InterProcessMultiLock;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.framework.recipes.locks.InterProcessSemaphoreMutex;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * Author: zhangxuepei
 * Date: 2020/3/18 21:24
 * Content:
 * 测试多锁对象,可以同时锁住多个对象。
 */
public class TestInterProcessMultiLock {
    public static void main(String[] args) throws Exception {
        // 可重入锁
        InterProcessLock interProcessLock1 = new InterProcessMutex(CuratorHelper.curatorFramework(), CuratorHelper.CURATOR_LOCK);
        // 不可重入锁
        InterProcessLock interProcessLock2 = new InterProcessSemaphoreMutex(CuratorHelper.curatorFramework(), CuratorHelper.CURATOR_LOCK);
        // 创建多重锁对象
        InterProcessLock lock = new InterProcessMultiLock(Arrays.asList(interProcessLock1, interProcessLock2));
        lock.acquire();
        lock.acquire(2, TimeUnit.SECONDS);
        interProcessLock1.acquire(2, TimeUnit.SECONDS);
        interProcessLock2.acquire(2, TimeUnit.SECONDS);
        lock.release();
        interProcessLock2.acquire(2, TimeUnit.SECONDS);
    }
}
