package threadpool;

import java.time.Instant;
import java.util.Random;
import java.util.concurrent.*;

import static java.lang.Thread.sleep;

/**
 * user:zxp
 * Day:2020,03,03
 *  使用这个周期任务调度线程池，
 *  比使用Timer定时器调度过程 时间精度更加准确。使用更加方便。
 *   异常处理方便
 *
 *   调用周期性任务 一般不用返回值，
 *   不错在竞争关系，所以可以使用主线程变量进行计算
 *    //通过Executors创建调度线程 核心线程数 根据地需要调度任务确定
 *    Executors.newSingleThreadScheduledExecutor();
 *    ScheduledFuture 返回值可以用于移除，取消等操作
 *     //设置Shutdown之后延时任务策略是否继续，和schedule配合使用
 *    setExecuteExistingDelayedTasksAfterShutdownPolicy()
 *    //设置Shutdown之后周期任务策略是否继续，和scheduleAtFixedRate()和scheduleWithFixedDelay()配合使用
 *    setContinueExistingPeriodicTasksAfterShutdownPolicy()
 *    //配合使用 取消之后的任务是否从队列中移除  默认是false，cancel之后不进行移除
 *    cancel(boolean)与setRemoveOnCancelPolicy()
 *    executor.setRemoveOnCancelPolicy(true);
 *    executor.cancel(true);
 *    //判断任务是否中断
 *    if（Thread.currentThread().isInterrupted() == true）
 **/
public class ScheduledThreadPool {
    private  Random random=new Random();
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ScheduledThreadPool scheduledThreadPool=new ScheduledThreadPool();
        scheduledThreadPool.testScheduleAtFixedRate();
    }

    /**
     * 测试周期任务执行
     *   如果周期过程中执行任务时间查过周期，按照执行任务时间作为依据
     */
    public void testScheduledAtFixRateTaskTimeOut(){
        ScheduledThreadPoolExecutor pool = new ScheduledThreadPoolExecutor(1);
         pool.scheduleAtFixedRate(() -> {
            int i = 2000 + random.nextInt(3) * 10000;
            Instant instant=Instant.now();
            System.out.println(instant+"run task, sleep :{}"+i);
            try {
                sleep(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, 1, 2, TimeUnit.SECONDS);  // 延迟 1s，周期 2s


    }

    /**
     * 测试周期任务执行
     *    不超过周期，按照周期进行执行
     */
    public void testScheduledAtFixRateTaskTimeIn(){
        ScheduledThreadPoolExecutor pool = new ScheduledThreadPoolExecutor(4);
        pool.scheduleAtFixedRate(() -> {
            int i = 1000;
            Instant instant=Instant.now();
            System.out.println(instant+"run task, sleep :{}"+i);
            try {
                sleep(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, 1, 2, TimeUnit.SECONDS);  // 延迟 1s，周期 2s
    }

    /**
     * 调用过程中  任务之间的时间间隔一样
     */
    public void testScheduleAtFixedRate() throws ExecutionException, InterruptedException {

        ScheduledThreadPoolExecutor pool = new ScheduledThreadPoolExecutor(1);
        ScheduledFuture sa=pool.scheduleWithFixedDelay(() -> {
            Instant instant=Instant.now();
            System.out.println("开始时间:"+instant);
            int i = 1000 + random.nextInt(5) * 1000;
            try {
                sleep(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Instant instant1=Instant.now();
            System.out.println(instant1+"run task, sleep :{}"+i);
        }, 1, 2, TimeUnit.SECONDS);  // 延迟 1s，周期 2s
        sa.cancel(true);
        pool.setRemoveOnCancelPolicy(true);
        System.out.println("dsad");
        System.out.println(sa.get());
    }
}
