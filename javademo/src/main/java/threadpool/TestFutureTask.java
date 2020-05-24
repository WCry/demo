package threadpool;

import java.util.concurrent.*;

/**
 * user:zxp
 * Day:2020,03,21
 * 测试FeatureTask 可以取消将来任务
 **/
public class TestFutureTask {
    private final ConcurrentMap<Object, Future<String>> taskCache = new ConcurrentHashMap<>();

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //利用FutureTask get阻塞方法  等待任务执行结束
        //不建议使用  没什么用处  除非是单个任务耗时
        //不阻塞主线程任务 主线程先执行其他的 等到摸个时刻自己在回来必须取结果的时候取出结果
        TestFutureTask testFutureTask = new TestFutureTask();
        testFutureTask.executionTask("test");
    }

    private String executionTask(final String taskName) throws ExecutionException, InterruptedException {
        Future<String> future = taskCache.get(taskName);
        if (future == null) {
            Callable<String> task = () -> taskName;
            FutureTask<String> futureTask = new FutureTask<String>(task);
            future = taskCache.putIfAbsent(taskName, futureTask);
            if (future == null) {
                future = futureTask;
                futureTask.run();
            }
        }
        try {
            return future.get();
        } catch (CancellationException e) {
            taskCache.remove(taskName, future);
        }
        return taskName;
    }
}
