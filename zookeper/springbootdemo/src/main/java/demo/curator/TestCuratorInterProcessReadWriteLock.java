package demo.curator;

import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.apache.curator.framework.recipes.locks.InterProcessReadWriteLock;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Author: zhangxuepei
 * Date: 2020/3/18 19:28
 * Content:
 *    测试Curator读写锁， 写锁是独享的，读锁是共享的
 *    读锁无法升级为写锁，
 *    获取写锁前，必须没有其他任何锁
 */
public class TestCuratorInterProcessReadWriteLock {
    public static void main(String[] args) throws Exception {
        // 创建读写锁对象, Curator 以公平锁的方式进行实现
        InterProcessReadWriteLock lock = new InterProcessReadWriteLock(CuratorHelper.curatorFramework(), CuratorHelper.CURATOR_LOCK);
        // lock2 用于模拟其他客户端
        InterProcessReadWriteLock lock2 = new InterProcessReadWriteLock(CuratorHelper.curatorFramework(), CuratorHelper.CURATOR_LOCK);
        // 使用 lock 模拟读操作
        InterProcessLock readLock = lock.readLock();
        InterProcessLock writeLock = lock.writeLock();
        InterProcessLock readLock2 = lock.readLock();
        InterProcessLock writeLock2 = lock2.writeLock();
        System.out.println("客户一开始写：" + writeLock.acquire(5, TimeUnit.SECONDS));
        System.out.println("客户二也要开始写：" + writeLock2.acquire(5, TimeUnit.SECONDS));
        System.out.println("客户二也要开始读：" + readLock2.acquire(5, TimeUnit.SECONDS));
        System.out.println("客户一也要开始读：" + readLock.acquire(5, TimeUnit.SECONDS));
        System.out.println("客户一开始再次写：" + writeLock.acquire(5, TimeUnit.SECONDS));
        System.out.println("客户一释放一次：");
        writeLock.release();
        System.out.println("客户二又要开始写：" + writeLock2.acquire(5, TimeUnit.SECONDS));
        System.out.println("客户一释放二次：");
        writeLock.release();
        System.out.println("客户二又要开始写：" + writeLock2.acquire(5, TimeUnit.SECONDS));
        System.out.println("可怜 读锁没有升级到写锁， 赶快释放度锁吧！");
        readLock.release();
        readLock2.release();
        System.out.println("客户二终于开始写：" + writeLock2.acquire(5, TimeUnit.SECONDS));


    }

}
