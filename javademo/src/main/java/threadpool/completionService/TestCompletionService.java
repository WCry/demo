package threadpool.completionService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @author zhangxuepei
 * @since 3.0
 * CompletionService 能够按照任务完成的顺序去取出结果
 */
public class TestCompletionService {
    public static void main(String[] args) {
        Long start = System.currentTimeMillis();
        //开启3个线程
        ExecutorService exs = Executors.newFixedThreadPool(5);
        try {
            int taskCount = 10;
            //结果集
            List<Integer> list = new ArrayList<Integer>();
            //1.定义CompletionService 顶一个完成的线程服务执行器能够按照执行完成的顺序接收结果
            CompletionService<Integer> completionService = new ExecutorCompletionService<>(exs);
            List<Future<Integer>> futureList = new ArrayList<>();
            //2.添加任务
            for (int i = 0; i < taskCount; i++) {
                futureList.add(completionService.submit(new Task(i + 1)));
            }
            //==================结果归集===================
            //方法1：future是提交时返回的，遍历queue则按照任务提交顺序，获取结果
            //            for (Future<Integer> future : futureList) {
            //                System.out.println("====================");
            //                Integer result = future.get();//线程在这里阻塞等待该任务执行完毕,按照
            //                System.out.println("任务result="+result+"获取到结果!"+new Date());
            //                list.add(result);
            //            }

            //            //方法2.使用内部阻塞队列的take()
            for (int i = 0; i < taskCount; i++) {
                //采用completionService.take()，内部维护阻塞队列，任务先完成的先获取到
                Integer result = completionService.take().get();
                System.out.println("任务i==" + result + "完成!" + new Date());
                list.add(result);
            }
            System.out.println("list=" + list);
            System.out.println("总耗时=" + (System.currentTimeMillis() - start));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            exs.shutdown();//关闭线程池
        }

    }

    static class Task implements Callable<Integer> {
        Integer i;
        public Task(Integer i) {
            super();
            this.i = i;
        }
        @Override
        public Integer call() throws Exception {
            if (i == 5) {
                Thread.sleep(5000);
            } else {
                Thread.sleep(1000);
            }
            System.out.println("线程：" + Thread.currentThread().getName() + "任务i=" + i + ",执行完成！");
            return i;
        }
    }
}
