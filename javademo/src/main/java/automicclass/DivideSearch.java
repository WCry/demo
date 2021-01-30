package automicclass;

import java.util.concurrent.CountedCompleter;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;


public class DivideSearch {
    public static void main(String[] args) {
        Integer[] array = new Integer[10000000];
        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1;
        }
        AtomicReference<Integer> result = new AtomicReference<>();
        Integer find = new Searcher<>(null, array, result, 0,
                array.length - 1, DivideSearch::match).invoke();
        System.out.println("查找结束,任务返回:{}" + find + ",result:{}" + result.get());
    }

    private static boolean match(Integer x) {
        return x > 2000000 && x % 2 == 0 && x % 3 == 0 && x % 5 == 0 && x % 7 == 0;
    }

    static class Searcher<E> extends CountedCompleter<E> {

        final E[] array;
        final AtomicReference<E> result;
        final int lo, hi;
        final Function<E, Boolean> matcher;

        Searcher(CountedCompleter<?> p, E[] array, AtomicReference<E> result,
                 int lo, int hi, Function<E, Boolean> matcher) {
            super(p);
            this.array = array;
            this.result = result;
            this.lo = lo;
            this.hi = hi;
            this.matcher = matcher;
        }

        @Override
        public void compute() {
            int l = this.lo;
            int h = this.hi;
            while (result.get() == null && h >= l) {

                if (h - l >= 2) {
                    int mid = (l + h) >>> 1;
                    //添加挂起任务数量,这样当出现tryComplete时可以触发root的结束(未查到)
                    addToPendingCount(1);
                    new Searcher<E>(this, array, result, mid, h, matcher).fork();
                    h = mid;
                } else {
                    E x = array[l];
                    if (matcher.apply(x) && result.compareAndSet(null, x)) {
                        super.quietlyCompleteRoot();
                    }
                    break;
                }
            }
            //当前未有任何一个线程查到结果,当前任务也完成了子集查找,减少一个挂起数量,若挂起数已减至0则终止.
            if (null == result.get())
                tryComplete();
        }

    }
}
