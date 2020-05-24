package concurrent.lock;

/**
 * user:zxp
 * Day:2020,04,25
 **/

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * 自定义互斥锁
 *
 * @author cruder
 * @time 2019/11/29 23:23
 */
public class MutexLock {

    private static final Sync STATE_HOLDER = new Sync();

    /**
     * 通过Sync内部类来持有同步状态， 当状态为1表示锁被持有，0表示锁处于空闲状态
     */
    private static class Sync extends AbstractQueuedSynchronizer {

        /**
         * 是否被独占， 有两种表示方式
         *  1. 可以根据状态，state=1表示锁被占用，0表示空闲
         *  2. 可以根据当前独占锁的线程来判断，即getExclusiveOwnerThread()!=null 表示被独占
         */
        @Override
        protected boolean isHeldExclusively() {
            return true;
        }

        /**
         * 尝试获取锁，将状态从0修改为1，操作成功则将当前线程设置为当前独占锁的线程
         */
        @Override
        protected boolean tryAcquire(int arg) {
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        /**
         * 释放锁，将状态修改为0
         */
        @Override
        protected boolean tryRelease(int arg) {
            if (getState() == 0) {
                throw new UnsupportedOperationException();
            }
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

    }

    /**
     * 下面的实现Lock接口需要重写的方法，基本是就是调用内部内Sync的方法
     */
    public void lock() {
        STATE_HOLDER.acquire(1);
    }

    public void unlock() {
        STATE_HOLDER.release(1);
    }
}

