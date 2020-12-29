package com.zxp.demo.flink.stream.process;

/**
 * @author zhangxuepei
 * @since 3.0
 */


import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.windowing.ProcessWindowFunction;
import org.apache.flink.streaming.api.windowing.windows.GlobalWindow;
import org.apache.flink.util.Collector;


/**
 * 测试ProcessWinFunction
 * 使用代价高，性能比较弱，
 * 此时算子需要对所有属于该窗口的接入数据进行缓存，
 * 然后等到窗口触发的时候，对所有的原始数据进行汇总计算。
 * 在某些情况下，统计更复杂的指标可能需要依赖窗口中所有的数据元素，
 * 或需要操作窗口中的状态数据和窗口元数据，
 * 例如：统计窗口数据元素中某一字段的中位数和众数
 */
public class TestProcessWinFunctionOnWindow {

    public static void main(String[] args) throws Exception{
        //获取执行环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        //读取数据
        DataStream<Tuple3<String,String,Long>> input = env.fromElements(ENGLISH);
        //求各班级英语成绩平均分
        DataStream<Double> avgScore = input.keyBy(0)
                //定义窗口的大小是2  如果窗口数量不够两个将不会往下走
                .countWindow(2)
                //窗口处理函数
                .process(new MyProcessWindowFunction());
        //对于计算记过进行Sink 这里的Sink 是简单的输出
        avgScore.print();
        env.execute("TestProcessWinFunctionOnWindow");
    }


    public static class MyProcessWindowFunction extends ProcessWindowFunction<Tuple3<String,String,Long>,Double, Tuple, GlobalWindow> {

        /**
         *  自定义窗口处理函数 对于窗口中元素进行统计等操作
         *   还有一个函数 就是定义清除窗口时候 如何处理上下文保存的内容
         * @param tuple  key
         * @param context  上下文
         * @param iterable  当前窗口的元素
         * @param out      输出集合
         * @throws Exception
         */
        @Override
        public void process(Tuple tuple, Context context, Iterable<Tuple3<String, String, Long>> iterable, Collector<Double> out) throws Exception {
            long sum = 0;
            long count = 0;
            for (Tuple3<String,String,Long> in :iterable){
                sum+=in.f2;
                count++;
            }
            out.collect((double)(sum/count));
        }
    }

    /**
     * 定义一个三元组的数据对象 作为输入流
     */
    public static final Tuple3[] ENGLISH = new Tuple3[]{
            Tuple3.of("class1","张三",100L),
            Tuple3.of("class2","小七",59L),
            Tuple3.of("class1","李四",78L),
            Tuple3.of("class1","王五",99L),
            Tuple3.of("class2","赵六",81L),
            Tuple3.of("class2","小八",97L),
            Tuple3.of("class2","小八2",97L),
            Tuple3.of("class1","王五2",99L),
    };
}
