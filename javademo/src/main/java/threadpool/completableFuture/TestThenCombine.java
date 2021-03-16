package threadpool.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author zhangxuepei
 * @since 3.0
 * 两个任务同时进行执行，然后等待两个任务执行完成之后，对于两个任务的结果进行合并
 */
public class TestThenCombine {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("第一个任务开始计算");
                Thread.sleep(1000);
                System.out.println("第一个任务计算完成");
            } catch (Exception e) {
            }
            return "Hello ";
            //集合后者的计算结果到前一个计算结果上面
            //参数另外一个异步计算， BiFunction接收两个参数返回一个结果
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("第二个任务开始计算");
                Thread.sleep(1000);
                System.out.println("第二个任务计算完成");
            } catch (Exception e) {
            }
            return "World";
            //T和U分别代表第一个任务的计算结果和第二个计算结果
        }), (s1, s2) -> Character.compare(s1.charAt(0),s2.charAt(0)));
        //get方法是阻塞的
        System.out.println(future.get());
    }
}
