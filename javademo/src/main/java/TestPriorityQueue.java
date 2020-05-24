import java.util.Comparator;
import java.util.PriorityQueue;

/** JAVA 优先级队列 实现对于加入队列的元素进行实时的排序 * */
public class TestPriorityQueue {
    public static void main(String[] args) {
        /**
         * 优先级队列比较器
         */
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer n1, Integer n2) {
                return (n1 < n2 ? -1 : n1 > n2 ? 1 : 0);
            }
        };
        //优先级队列 初始化大小和设置比较器
        PriorityQueue queue = new PriorityQueue(10, comparator);
        queue.add(5);
        queue.add(3);
        queue.add(10);
        //java 8 函数式编程进行输出
        queue.forEach(System.out::println);
    }
}
