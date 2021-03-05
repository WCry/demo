package threadpool.forkjoin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CountedCompleter;

/**
 * @author zhangxuepei
 * @since 3.0
 */
public class TestLeafCollection {
    public static void main(String[] args) {
        List<List<String>> countCollections = new ArrayList<>();
        countCollections.add(new ArrayList<>(Arrays.asList("ddd", "dsd")));
        countCollections.add(new ArrayList<>(Arrays.asList("ddd", "dsd")));
        countCollections.add(new ArrayList<>(Arrays.asList("ddd")));
        LeafCountWork leafCountWork = new LeafCountWork(null, countCollections, null);
        Integer count = leafCountWork.invoke();
        System.out.println(count);
    }

    static class LeafCountWork extends CountedCompleter<Integer> {

        private int count;
        private List branches;
        private LeafCountWork forks, next;

        public LeafCountWork(CountedCompleter<?> completer, List branches, LeafCountWork next) {
            super(completer);
            this.branches = branches;
            this.next = next;
        }

        @Override
        public void compute() {
            for (int i = 0; i < branches.size(); i++) {
                List list;
                Object obj = branches.get(i);
                if (obj instanceof String) {
                    //叶子节点
                    this.count++;
                } else {
                    addToPendingCount(1);
                    list = (List) obj;
                    forks = new LeafCountWork(this, list, forks);
                    forks.fork();
                }
            }

            for (LeafCountWork c = (LeafCountWork) firstComplete(); Objects.nonNull(c); c =
                    (LeafCountWork) c.nextComplete()) {
                for (LeafCountWork t = c, s = t.forks; Objects.nonNull(s); s = t.forks = s.next) {
                    //此处为模拟业务，统计数量
                    t.count += s.count;
                }
            }
        }

        @Override
        public Integer getRawResult() {
            return count;
        }
    }
}
