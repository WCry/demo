package demo.curator;

import org.apache.curator.framework.recipes.locks.InterProcessSemaphore;
import org.apache.curator.framework.recipes.locks.InterProcessSemaphoreV2;
import org.apache.curator.framework.recipes.locks.Lease;
import org.apache.curator.framework.recipes.shared.SharedCount;
import org.apache.curator.framework.recipes.shared.SharedCountReader;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

/**
 * Author: zhangxuepei
 * Date: 2020/3/18 19:47
 * Content:
 *    测试信号量锁，允许一定数量的应用进行共同操作
 *      每一个应用最大组约数，min(当前所有设置组约数求和/参与应用数量,自己最大组约数）
 *      叫做能者多设置组约数，自有自己参与协作时候能够获取最大组约数
 *      当在他之前已有参与者时候 自身就不能超过平均数
 *      所以在协调的时候都设置成为一样的数量 租约均等
 *    sharedCountReader.start();
 *    sharedCountReader.setCount(10);
 */
public class TestInterProcessSemaphoreV2 {
    public static void main(String[] args) throws Exception {
        SharedCount sharedCount=new SharedCount(CuratorHelper.curatorFramework(),
                CuratorHelper.CURATOR_LOCK,6);
        sharedCount.start();
        //客户1占用 两个续约
        InterProcessSemaphoreV2 semaphore1 = new InterProcessSemaphoreV2(CuratorHelper.curatorFramework(),
                CuratorHelper.CURATOR_LOCK, 3);
        //客户2牛逼 占用4个续约
        InterProcessSemaphoreV2 semaphore2 = new InterProcessSemaphoreV2(CuratorHelper.curatorFramework(),
                CuratorHelper.CURATOR_LOCK, 10);
        Collection<Lease> leases=semaphore1.acquire(3);
        System.out.println("客户一获取租约干事情");
//        //获取到两个租约开始干事情， 千万不要获取的多了， 多了没办法就阻塞了
        //Collection<Lease> leases=semaphore1.acquire(7);
        System.out.println("客户一获取租约干事情");
        Collection<Lease> leases2=semaphore2.acquire(6);
        System.out.println("客户二获取租约干事情");
        Collection<Lease> leases3=semaphore2.acquire(3);
        System.out.println("客户二获取租约干事情");








//        // 释放一个许可
//       // semaphore.returnLease(lease);
//        semaphore2.returnLease(lease2);
//        // 释放多个许可
//        shedlerSemaphore.returnAll(leases);
//        semaphore2.returnAll(leases2);
    }
}
