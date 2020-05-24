package demo.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.framework.recipes.locks.InterProcessSemaphoreMutex;
import org.apache.curator.retry.RetryNTimes;
import org.springframework.util.Assert;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Author: zhangxuepei
 * Date: 2020/3/17 19:53
 * Content:
 *    实现上是基于单个信号量共享锁实现
 *   测试Curator 基于信号量的互斥锁，
 *   不支持重入
 */
public class TestCuratorInterProcessSemaphoreMutex {
    // 锁节点
    private static final String CURATOR_LOCK = "/curatorLock";
    // 此demo使用的集群，所以有多个ip和端口
    private static String CONNECT_SERVER = "10.19.154.187:2181,10.19.154.188:2181,10.19.154.189:2181,10.19.154.191:2181,10.19.154.192:2181";
    // session过期时间
    private static int SESSION_TIMEOUT = 3000;
    // 连接超时时间
    private static int CONNECTION_TIMEOUT = 3000;

    public static void main(String[] args) throws Exception {
        // 连接 ZooKeeper
        CuratorFramework framework1 = CuratorFrameworkFactory.
                newClient(CONNECT_SERVER, SESSION_TIMEOUT, CONNECTION_TIMEOUT, new RetryNTimes(10, 5000));
        framework1.start();
        CuratorFramework framework2 = CuratorFrameworkFactory.
                newClient(CONNECT_SERVER, SESSION_TIMEOUT, CONNECTION_TIMEOUT, new RetryNTimes(10, 5000));
        framework2.start();
        // 创建基于信号量的互斥锁 内部实现 是基于信号量数为1实现互斥的目的
        InterProcessLock lock = new InterProcessSemaphoreMutex(framework1, CURATOR_LOCK);
        InterProcessLock lock2 = new InterProcessSemaphoreMutex(framework2, CURATOR_LOCK);
        //测试互斥 无法多个应用多次获取锁
        System.out.println("第一个客户获取锁："+lock.acquire(2, TimeUnit.SECONDS));
        System.out.println("第一个客户再次获取锁："+lock.acquire(2, TimeUnit.SECONDS));
        System.out.println("第二个客户获取锁："+lock2.acquire(2, TimeUnit.SECONDS));
        //释放锁, 不支持重入 所以这里只有一个获取成功 只需要释放一次
        lock.release();
    }
}
