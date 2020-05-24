import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * 对于JAVA 流计算
 *
 * 对于Stream 的性能测试
 * https://www.cnblogs.com/CarpenterLee/p/6675568.html
 *
 * 多核环境性能提高很多，  一般现在服务都是多核服务器，所以放心使用
 */
public class StreamTest {
    public static void main(String[] args) {
        List<String> testString= Arrays.asList("abc", "bc", "bc", "efg", "abcd","", "jkl");
        //使用流进行统计单词
        System.out.println("统计总数:"+testString.parallelStream().filter(a -> {
            return a.equals("bc");
        }).count());
        //使用并行流进行过滤
        testString=testString.parallelStream().filter(a->{return a.equals("bc");}).collect(Collectors.toList());
        testString.forEach(System.out::println);
                List<String> words = Arrays.asList("Hello", "World");
      //  Stream.concat(Stream.of("Java", "Python"), Stream.of("C++", "Ruby")).forEach(System.out::println);

        System.out.println(words.stream().reduce("", (a, b) -> a + " " + b));

        words.stream().map(String::length) //转为字符串长度的集合
                .collect(Collectors.toList()).forEach(System.out::println);
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        //将数组转换成为流
        int sum = Arrays.stream(numbers).sum();

        System.out.println(LongStream.rangeClosed(0, 5)
                //顺序流
                .sequential().reduce(0, Long::sum));
        //迭代
        Stream.iterate(0, n -> n + 2).limit(10).forEach(System.out::println);
        LongStream.rangeClosed( 0,110);
    }
}
