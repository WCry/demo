/**
 *
 * 参考实现
 *   https://segmentfault.com/a/1190000015562456
 *   https://www.cnblogs.com/jyroy/p/11365935.html
 *
 *  锁的概念：
 *     同步方式区分, 乐观锁   ->不加锁，拷贝副本，修改，还原 CAS
 *                   悲观锁   ->一直需要先加上锁，才能操作
 *    锁是否能够共享：共享锁  -> 比如   读锁
 *                    独占锁  ->修改值  独占
 *    对于线程是否公平：
 *                    公平锁   ->先到先得，利用队列记录需要竞争资源的线程
 *                    非公平锁 ->抢占
 *    等待锁的过程方式
 *                   自旋锁  ->等待过程中一个进行自旋尝试获取锁，适应性自旋锁
 *                    阻塞锁  ->等待过程中什么也不做，等待被唤醒在进行操作 会进行内核态和线程态之间转换，可能会切换耗时
 *    根据锁是否能够被一个线程重复获取：
 *                    重入锁  ->线程在获取锁之后，能够再次获取锁。  多次重入，多次释放
 *                    非重入锁 -> 获取锁的线程不能再次进入线程
 *    具体锁的：
 *            ReentrantLock          互斥锁唯一个对外  继承 Lock的实现锁 代码中使用 悲观锁
 *            ReentrantWriteLock    读写锁 包含两个锁，读锁写锁。
 *            synchronized          JVM 实现的 （通过jvm参数设置，使用锁的类型，偏向锁 轻量级锁 重量级锁）
 *            LockSupport           LockSupport.park();LockSupport.unpark(); jdk中提供对于锁的支持，一般很少使用。
 *            AtomicBoolean         原子操作类 使用的是乐观锁  A->B->A 问题  使用的是自旋的方式
 *            AtomicReference       原子类操作对象乐观锁，对于多个变量的原子操作
 *            LongAdder  DoubleAdder    DoubleAccumulator   LongAccumulator  对于长整型，double的 更高并发操作的原子性 https://www.jianshu.com/p/ec045c38ef0c
 *    基于锁实现的类：
 *            CountDownLatch         计数器等待 固定数量 知道减少到0  做到线程任务同步
 *            CyclicBarrier          回环栅栏  可以重复使用  做到线程任务同步
 *            Semaphore              信号量       最多有多少任务可以同时执行
 *            StampedLock            邮戳锁，能够实现读写锁之间的转换 不支持重入
 *            CopyOnWriteArrayList   CopyOnWriteArraySet 多线程环境下 读写分离
 *     其他需要自己实现的锁：
 *          自旋锁的自己实现：  https://blog.csdn.net/fuyuwei2015/article/details/83387536
 *
 *  线程同步操作
 *     测试JAVA中锁 synchronized java内部实现线程同步锁
 *     有JVM进行控制所锁的级别，自动进行升级，偏向锁(一个线程竞争时候)->轻量级锁（）->重量级锁（多线程竞争时候）
 *
 *  jdk中自旋锁：  jdk1.6之前需要设置
 *                 jdk1.7之后不需要设置，有JVM进行控制
 *
 *
 *   锁优化
 *        消除缓存行的伪共享  在jdk1.8中通过添加sun.misc.Contended注解来解决这个问题，若要使该注解有效必须在jvm中添加以下参数：
 *                            -XX:-RestrictContended
 *
 */
package concurrent;