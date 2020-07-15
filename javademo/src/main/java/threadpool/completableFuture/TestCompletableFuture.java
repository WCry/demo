package threadpool.completableFuture;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhangxuepei
 * @since 3.0
 * https://www.jianshu.com/p/73aaec23009d
 * https://leokongwq.github.io/2017/11/30/java8-CompletableFuture-1.html
 */
public class TestCompletableFuture {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        // 结果集
        List<String> list = new ArrayList<>();
        //声明执行的线程池
        ExecutorService executorService = Executors.newFixedThreadPool(8);
        //任务列表
        List<Integer> taskList = Arrays.asList(2, 1, 3, 4, 5, 6, 7, 8, 9, 10);
        // 全流式处理转换成CompletableFuture[]+组装成一个无返回值CompletableFuture，join等待执行完毕。返回结果whenComplete获取
        CompletableFuture[] cfs = taskList.stream()
                //这个map操作简单的将对象传递给CompletableFuture.supplyAsync的异步方法
                .map(integer -> CompletableFuture.supplyAsync(() -> calc(integer), executorService)
                        //异步犯法执行完成之后将整型的参数转换成为字符串
                        //ThenApply是上一步完成之后，对于上一步完成的结果进行转换
                        .thenApply(h->Integer.toString(h))
                        //异步执行完成之后将结果增加到List当中，结果完成之后之后执行,代表完成所有的计算
                        .whenComplete((s, e) -> {
                            //打印结果
                            System.out.println("任务"+s+"完成!result="+s+"，异常 e="+e+","+new Date());
                            list.add(s);
                        })
                        //将一个流操作转换成为一个数组，这里因为流中的方法是异步执行，
                        //返回的是结果的引用，异步方法不一定执行完成
                ).toArray(CompletableFuture[]::new);
        // 封装后无返回值，必须自己whenComplete()获取
        //线程join等待全部执行完毕
        CompletableFuture.allOf(cfs).join();
        System.out.println("list="+list+",耗时="+(System.currentTimeMillis()-start));
        executorService.shutdown();
    }


    public static int calc(Integer i) {
        try {
            if (i == 1) {
                Thread.sleep(3000);//任务1耗时3秒
            } else if (i == 5) {
                Thread.sleep(5000);//任务5耗时5秒
            } else {
                Thread.sleep(1000);//其它任务耗时1秒
            }
            System.out.println("task线程：" + Thread.currentThread().getName() + "任务i=" + i + ",完成！+" + new Date());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return i;
    }
}
