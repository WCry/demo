package threadpool;

import java.util.Random;
import java.util.concurrent.*;

/**
 * user:zxp
 * Day:2020,03,03
 *   任务划分线程池
 **/
public class ForkJoinPoolRecursiveTaskTest extends RecursiveTask<Integer> {
    /**
     *  每个"小任务"最多只打印70个数
     */
    private static final int MAX = 70;
    private int arr[];
    private int start;
    private int end;
    public ForkJoinPoolRecursiveTaskTest(int[] arr, int start, int end) {
        this.arr = arr;
        this.start = start;
        this.end = end;
    }
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int arr[] = new int[1000];
        Random random = new Random();
        int total = 0;
        // 初始化100个数字元素
        for (int i = 0; i < arr.length; i++) {
            int temp = random.nextInt(100);
            // 对数组元素赋值,并将数组元素的值添加到total总和中
            total += (arr[i] = temp);
        }
        System.out.println("初始化时的总和=" + total);
        // 创建包含Runtime.getRuntime().availableProcessors()返回值作为个数的并行线程的ForkJoinPool
        Executors.newWorkStealingPool();

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        // 提交可分解的PrintTask任务
        Future<Integer> future = forkJoinPool.submit(new ForkJoinPoolRecursiveTaskTest(arr, 0, arr.length));
        System.out.println("计算出来的总和="+future.get());
//        Integer integer = forkJoinPool.invoke( new RecursiveTaskTest(arr, 0, arr.length)  );
//        System.out.println("计算出来的总和=" + integer);
        // 关闭线程池
        forkJoinPool.shutdown();
    }
    @Override
    protected Integer compute() {
        int sum = 0;
        // 当end-start的值小于MAX时候，开始打印
        if((end - start) < MAX) {
            for (int i = start; i < end; i++) {
                sum += arr[i];
            }
            return sum;
        }else {
            System.err.println("=====任务分解======");
            // 将大任务分解成两个小任务
            int middle = (start + end) / 2;
            ForkJoinPoolRecursiveTaskTest left = new ForkJoinPoolRecursiveTaskTest(arr, start, middle);
            ForkJoinPoolRecursiveTaskTest right = new ForkJoinPoolRecursiveTaskTest(arr, middle, end);
            // 并行执行两个小任务
            left.fork();
            right.fork();
            // 把两个小任务累加的结果合并起来
            return left.join() + right.join();
        }
    }
}
