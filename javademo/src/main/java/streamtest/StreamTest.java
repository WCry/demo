package streamtest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhangxuepei
 * @since 3.0
 */
public class StreamTest {
    public static void main(String[] args) {
        testFilter();
    }
    public static void testFilter() {
        List<String> filterData=new ArrayList<>();
        filterData.add("A");
        filterData.add("B");
        List filterResult= filterData.parallelStream().filter(s -> s.equals("A")).
                collect(Collectors.toList());
        System.out.println(filterResult);
    }
    
    public static void testReduce() {
        List<Integer> reduceData=new ArrayList<>();
        reduceData.add(1);
        reduceData.add(2);
        //Reduce 存在一个三参数  一个基础值  一个是串行化计算使用的参数
        //一个并行方式执行的参数
        Integer sumResult= reduceData.parallelStream().
                reduce(0,Integer::sum);
        System.out.println(sumResult);
    }
}


