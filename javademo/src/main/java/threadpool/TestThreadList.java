package threadpool;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhangxuepei
 * @since 3.0
 */
public class TestThreadList {
    List<Integer> testList = new LinkedList<>();

    private TestThreadList() {
        testList.add(1);
        testList.add(2);
        testList.add(3);
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            executorService.execute(() -> post(Thread.currentThread().getId()));
        }
        executorService.shutdown();
    }

    public static void main(String[] args) {
        TestThreadList testThreadList=new TestThreadList();
    }

    private void post(long number) {
        //对于ArrayList的对象，采用的是方法中遍历数组的方式
        //对于LinkedList 的迭代器是是新生成一个对象，保存next等信息，进行遍历。
        //所以是多线程安全的
        for (Integer integer : testList) {
            System.out.println("线程：" + number +"值："+ integer);
        }
//        //多线程遍历是安全的
//        testList.forEach(value -> {
//            System.out.println("线程：" + number +"值："+ value);
//        });
    }
}
