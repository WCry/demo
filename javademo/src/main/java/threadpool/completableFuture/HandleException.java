package threadpool.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author zhangxuepei
 * @since 3.0
 * 任务执行结束的时候进行执行，即使出现异常也是会执行，其中包括异常信息
 * future.handle
 */
public class HandleException {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        String name = null;
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            if (name == null) {
                throw new RuntimeException("Computation error!");
            }
            return 2;
        }).handle((s,t)->{
            System.out.println("出现异常");
            //处理异常 异常如何进行处理
            return "d";
        }).thenApply(w->{
            System.out.println(w);
            System.out.println("转换过程执行");
            return w.toString();
       })
        .handle((s, t) -> {
            //handle 异常处理
            if(t!=null){
                System.out.println(t.getMessage());
            }
            return s != null ? s : "Hello, Stranger!";
        });
        //获取正在等待完成的任务
        System.out.println(future.get());
    }
}
