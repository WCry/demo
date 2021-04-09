package streamtest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

/**
 * @author zhangxuepei
 * @since 3.0
 */
public class StreamTest {
    public static void main(String[] args) {
        //testFilter();
        testGroupBy3();
    }

    public static void testFilter() {
        List<String> filterData = new ArrayList<>();
        filterData.add("A");
        filterData.add("B");
        List filterResult = filterData.parallelStream().filter(s -> s.equals("A")).
                collect(Collectors.toList());
        System.out.println(filterResult);
    }

    public static void testReduce() {
        List<Integer> reduceData = new ArrayList<>();
        reduceData.add(1);
        reduceData.add(2);
        //Reduce 存在一个三参数  一个基础值  一个是串行化计算使用的参数
        //一个并行方式执行的参数
        Integer sumResult = reduceData.parallelStream().
                reduce(0, Integer::sum);
        System.out.println(sumResult);
    }

    public static void testGroupBy() {
        List<Student> studentList=prepareData();
        //简单通过Name进行分组
        Map<String, List<Student>> stringListGroupByName = studentList.stream().
                collect(Collectors.groupingBy(Student::getName));
        System.out.println(stringListGroupByName);
    }
    public static void testGroupBy3() {
        ConcurrentMap<Long, String> oldMap=new ConcurrentHashMap<>();
        oldMap.putIfAbsent(11L,"55");
        Map<Long, String> testMap=LongStream.rangeClosed(1,10).boxed()
                .collect(Collectors.
                        //可以直接实现 map reduce两个过程
                        //可以生成一个并发线程安全的集合当中
                        toConcurrentMap(
                        //如何获取到Key的值
                        i -> i,
                        //如何获取到Value值
                        i -> "a",
                        //存在重复的时候如何进行处理
                        (o1, o2) -> o1,
                        //将映射的结果保存到已有的集合中，或者一个新的集合中
                        () -> oldMap));
        System.out.println(testMap);
    }
    public static void testGroupBy2() {
        List<Student> studentList=prepareData();
        //先进行分组  进行映射  进行求和
        //使用过程需要注意并发 并发安全groupingByConcurrent()
        Map<String, Integer> stringListGroupByName = studentList.stream().
                collect(Collectors.groupingBy(Student::getName,
                        Collectors.mapping(Student::getScore,Collectors.reducing(0,Integer::sum))));
        System.out.println(stringListGroupByName);
    }

    private static List<Student> prepareData(){
        Student student = new Student();
        student.setName("A");
        student.setScore(1);
        Student student1 = new Student();
        student1.setName("B");
        student1.setScore(1);
        Student student2 = new Student();
        student2.setName("A");
        student2.setScore(1);
        Student student3 = new Student();
        student3.setName("C");
        student3.setScore(1);
        Student student4 = new Student();
        student4.setName("B");
        student4.setScore(1);
        List<Student> studentList=Arrays.asList(student,student1,student2,student2,student3,student4);
        return studentList;
    }

    static class Student {
        String name;
        Integer score;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getScore() {
            return score;
        }

        public void setScore(Integer score) {
            this.score = score;
        }

        @Override
        public String toString() {
            return "Time.Student{" +
                    "name='" + name + '\'' +
                    ", score=" + score +
                    '}';
        }
    }
}


