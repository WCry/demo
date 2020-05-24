package demo.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;

/**
 * Author: zhangxuepei
 * Date: 2020/3/18 19:11
 * Content:
 * 参考地址：https://www.jianshu.com/p/31335efec309
 * https://blog.csdn.net/kiss_the_sun/article/details/50221463
 * 对于锁的类型有问题，
 * 不应该是共享锁，应该是排它锁 一次值能够有一个获取锁成功
 * 读锁是共享锁
 */
public class CuratorHelper {
    // 锁节点
    public static final String CURATOR_LOCK = "/curatorLock";
    // 此demo使用的集群，所以有多个ip和端口
    private static String CONNECT_SERVER = "10.19.154.187:2181,10.19.154.188:2181,10.19.154.189:2181,10.19.154.191:2181,10.19.154.192:2181";
    // session过期时间
    private static int SESSION_TIMEOUT = 3000;
    // 连接超时时间
    private static int CONNECTION_TIMEOUT = 3000;

    public static CuratorFramework curatorFramework(){
        // 连接 ZooKeeper
        CuratorFramework framework1 = CuratorFrameworkFactory.
                newClient(CONNECT_SERVER, SESSION_TIMEOUT, CONNECTION_TIMEOUT, new RetryNTimes(10, 5000));
        framework1.start();
        return framework1;
    }
}
