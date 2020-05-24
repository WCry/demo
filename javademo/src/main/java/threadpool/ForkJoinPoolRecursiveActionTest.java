package threadpool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;

/**
 * user:zxp
 * Day:2020,03,03
 *  递归进行划分任务进行，
 *         不需要返回结果
 *   任务划分线程池
 **/
public class ForkJoinPoolRecursiveActionTest extends RecursiveAction {
    /**
     * 每个"小任务"最多只打印20个数
     */
    private static final int MAX = 20;
    private int start;
    private int end;

    public ForkJoinPoolRecursiveActionTest(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public static void main(String[] args) throws InterruptedException {
        // 创建包含Runtime.getRuntime().availableProcessors()返回值作为个数的并行线程的ForkJoinPool
        System.out.println("可以利用处理器："+Runtime.getRuntime().availableProcessors());
        //默认最大线程数量是 CPU数量
        ForkJoinPool forkJoinPool = new ForkJoinPool(Runtime.getRuntime().
                availableProcessors());
        // 提交可分解的PrintTask任务
        forkJoinPool.submit(new ForkJoinPoolRecursiveActionTest(0, 1000));
        // 关闭线程池
        forkJoinPool.shutdown();
        //阻塞当前线程直到 ForkJoinPool 中所有的任务都执行结束
        forkJoinPool.awaitTermination(2, TimeUnit.SECONDS);
    }
    @Override
    protected void compute() {
        //当end-start的值小于MAX时，开始打印
        if ((end - start) < MAX) {
            for (int i = start; i < end; i++) {
                System.out.println(Thread.currentThread().getName() + "i的值" + i);
            }
        } else {
            // 将大任务分解成两个小任务
            int middle = (start + end) / 2;
            ForkJoinPoolRecursiveActionTest left = new ForkJoinPoolRecursiveActionTest(start, middle);
            ForkJoinPoolRecursiveActionTest right = new ForkJoinPoolRecursiveActionTest(middle, end);
            left.fork();
            right.fork();
        }
    }
}
