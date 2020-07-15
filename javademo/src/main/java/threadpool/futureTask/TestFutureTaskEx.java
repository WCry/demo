package threadpool.futureTask;

import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * user:zxp
 * Day:2020,03,21
 **/
public class TestFutureTaskEx {
    private final ConcurrentMap<Object, Future<String>> taskResult = new ConcurrentHashMap<>();
    private final ConcurrentMap<Object, String> taskCache = new ConcurrentHashMap<>();

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //利用FutureTask get阻塞方法  等待任务执行结束
        //不建议使用  没什么用处  除非是单个任务耗时
        //不阻塞主线程任务 主线程先执行其他的 等到摸个时刻自己在回来必须取结果的时候取出结果
        TestFutureTaskEx testFutureTask = new TestFutureTaskEx();
        System.out.println(testFutureTask.executionTask("test"));
    }

    private String executionTask(final String taskName) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<String> futureTask = taskResult.get(taskName);
        if (futureTask == null) {
            Callable<String> task = () -> {
                Thread.sleep(1000);
                return taskName;
            };
             futureTask = executor.submit(task);
            taskResult.putIfAbsent(taskName, futureTask);
        }
        try {
            System.out.println("开始获取get结果");
            String result = futureTask.get();
            System.out.println("获取到结果");
            return result;
        } catch (CancellationException e) {
            taskResult.remove(taskName, futureTask);
        }
        return taskName;
    }
}
