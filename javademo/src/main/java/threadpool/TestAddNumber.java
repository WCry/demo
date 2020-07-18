package threadpool;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.*;

/**
 * user:zxp
 * Day:2020,07,16
 **/
public class TestAddNumber {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int threadCount = 5;
        int maxNumber = 10000;
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(threadCount,
                threadCount,
                5,
                TimeUnit.SECONDS, new LinkedBlockingDeque<>());
        List<Future<Integer>> cs = new ArrayList<>();
        int step = maxNumber / threadCount;
        int stepStart = 0;
        int stepEnd = 0;
        for (int i = 0; i < threadCount; i++) {
            stepEnd = stepStart + step;
            if(stepEnd==maxNumber){
                cs.add(threadPoolExecutor.submit(new SumNumber(stepStart, stepEnd,true)));
            }else{
                cs.add(threadPoolExecutor.submit(new SumNumber(stepStart, stepEnd,false)));
            }
            stepStart = stepEnd;
        }
        int sum = 0;
        Iterator<Future<Integer>> iterator= cs.iterator();
        while (iterator.hasNext()){
            Future<Integer> res= iterator.next();
            sum += res.get();
            iterator.remove();
        }
        threadPoolExecutor.shutdown();
        System.out.println(sum);
    }

    static class SumNumber implements Callable<Integer> {
        private int start;
        private int end;
        private Boolean isend=false;
        SumNumber(int start, int end, Boolean isend) {
            this.start = start;
            this.end = end;
            this.isend=isend;
        }

        @Override
        public Integer call() throws Exception {
            int sum = 0;
            for (int w = start; w < end; w++) {
                sum += w;
            }
            if(isend){
                sum+=end;
            }
            return sum;
        }
    }
}
