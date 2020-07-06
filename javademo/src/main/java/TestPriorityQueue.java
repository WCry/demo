import java.util.Comparator;
import java.util.PriorityQueue;

/** JAVA 优先级队列 实现对于加入队列的元素进行实时的排序 * */
public class TestPriorityQueue {
    public static void main(String[] args) {
        /**
         * 优先级队列比较器
         */
        Comparator<Integer> comparator =(x,y)-> (x < y) ? -1 : ((x == y) ? 0 : 1);
        //优先级队列 初始化大小和设置比较器
        PriorityQueue queue = new PriorityQueue(10, comparator);
        queue.add(10);
        queue.add(5);
        queue.add(3);

        queue.add(5);
        queue.add(6);
        queue.add(8);
       // queue.remove(5);
        //java 8 函数式编程进行输出
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        //注意只有 poll才会按照顺序， foreach不会按照顺序
        //采用的是大顶堆实现， 在增加和移除的时候都进行不断调整
        queue.forEach(System.out::println);
    }
}
