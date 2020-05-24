package threadpool;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池 执行器
 */
public class TestThreadPoolExecutor {
    //处理任务的行数
    private static int rowCount = 50;
    //处理任务的列数
    private static int levelCount = 10;
    //线程处理队列 是一个链表阻塞队列
    private LinkedBlockingDeque linkedBlockingDeque = new LinkedBlockingDeque();
    //启动线程池进行处理
    private ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 20,
            20, TimeUnit.SECONDS, linkedBlockingDeque);
    //线程处理结
    private List<Future<String>> results = new LinkedList<>();
    //最大允许排队数是50
    private int maxQue = 50;
    //单个线程一次处理20个切片
    private int countSimple = 20;
    //子任务
    private List<Integer> needSub = new ArrayList<>(countSimple);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        TestThreadPoolExecutor testThreadPoolExecutor = new TestThreadPoolExecutor();
        for (int level = 0; level < levelCount; level++) {
            for (int row = 0; row < rowCount; row++) {
                if (row < rowCount - 1) {
                    testThreadPoolExecutor.addTask(false, false,level + " row " + row);
                } else {
                    if (level != levelCount - 1) {
                        testThreadPoolExecutor.addTask(false, true,level + " row " + row);
                    } else {
                        testThreadPoolExecutor.addTask(true, true,level + " row " + row);
                    }
                }
            }
        }
    }

    /**
     * 任务中添加队列
     * @param allEnd 是否是全部都结束
     * @param tile  需要处理的数据
     * @param levelEnd  是否是某个级别结束
     * @throws ExecutionException 执行过程异常
     * @throws InterruptedException 中断异常
     */
    private void addTask(Boolean allEnd,Boolean levelEnd, String tile) throws ExecutionException, InterruptedException {
        needSub.add(30);
        if (levelEnd || needSub.size() >= countSimple - 1) {
            //进行提交任务
            submit(allEnd, tile, needSub);
            needSub = new ArrayList<>(countSimple);
        }
    }

    /**
     * 处理任务
     * @param allEnd
     * @param level
     * @param task
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private void submit(Boolean allEnd, String level, List<Integer> task) throws ExecutionException, InterruptedException {
        //如果队列比较长了 进行循环
        results.add(threadPoolExecutor.submit(new DownloadTask(level, task)));
        if (allEnd) {
            threadPoolExecutor.shutdown();
            getResult(true);
        } else {
            while (linkedBlockingDeque.size() > maxQue) {
                getResult(false);
            }
        }
    }

    /**
     * 获取结果
     * @param isAllEnd
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private void getResult(Boolean isAllEnd) throws ExecutionException, InterruptedException {
        Iterator<Future<String>> resultIter = results.iterator();
        while (resultIter.hasNext()) {
            try{
                System.out.println(resultIter.next().get());
            }catch (Exception ex){
                 //输出异常
            }finally {
                //操作完成从结果集合中移出掉 不管有没有异常 都有返回
                resultIter.remove();
            }
            if (!isAllEnd) {
                if (linkedBlockingDeque.size() < maxQue) {
                    break;
                }
            }
        }
    }
}
