package threadpool.forkjoin.mannger;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

public  class CallBackTest{
    public static void main(String[] args) throws Exception {
        CallBackTest callBackTest=new CallBackTest();
        callBackTest.testWorkStealing();
    }
    public void testWorkStealing() throws Exception {
        final int parallelism = 4;
        final ExecutorService pool = new ForkJoinPool(parallelism);
        final CyclicBarrier barrier = new CyclicBarrier(parallelism);

        final List<CallableTask> callableTasks = Collections.nCopies(parallelism, new CallableTask(barrier));
        int result = pool.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                int result = 0;
                // Deadlock in invokeAll(), rather than stealing work
                for (Future<Integer> future : pool.invokeAll(callableTasks)) {
                    result += future.get();
                }
                return result;
            }
        }).get();
        System.out.println(result);
    }
    static class CallableTask implements Callable<Integer> {
        private final CyclicBarrier barrier;

        CallableTask(CyclicBarrier barrier) {
            this.barrier = barrier;
        }

        @Override
        public Integer call() throws Exception {
            System.out.println(Thread.currentThread());
            barrier.await();
            return 1;
        }
    }
}



