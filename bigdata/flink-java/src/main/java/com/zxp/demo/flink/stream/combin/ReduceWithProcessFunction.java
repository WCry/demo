package com.zxp.demo.flink.stream.combin;

import org.apache.flink.api.common.functions.ReduceFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.source.SourceFunction;
import org.apache.flink.streaming.api.functions.windowing.ProcessWindowFunction;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;

import java.util.Random;

/**
 * @author zhangxuepei
 * @since 3.0
 * 自定义规约函数  求最大值
 */
public class ReduceWithProcessFunction {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.getConfig().setAutoWatermarkInterval(200L);
        DataStream<Tuple2<String, Long>> dataStreamSource = env.addSource(new SourceFunction<Tuple2<String, Long>>() {
            private volatile Boolean isRunning = true;

            @Override
            public void run(SourceContext<Tuple2<String, Long>> sourceContext) throws Exception {
                int i = 0;
                String[] names = new String[]{"张三", "李四", "王五", "麻六", "刘七"};
                Random random = new Random();
                int currentNumber = 100;
                while (isRunning) {
                    //Tuple2 sourceData = new Tuple2<>(names[i % 5], new Long(random.nextInt(100)));
                    Tuple2 sourceData = new Tuple2<>(names[i % 5], new Long(currentNumber));
                    sourceContext.collect(sourceData);
                    if (i % 10 == 0) {
                        Thread.sleep(100);
                        currentNumber = 100 - i;
                    }
                    i++;
                }
            }

            @Override
            public void cancel() {
                isRunning = false;
            }
        });



        SingleOutputStreamOperator<Tuple2<Long, Tuple2<String, Long>>> result = dataStreamSource.keyBy(t -> t.f0).
                //定义窗口
                timeWindow(Time.seconds(10000))
                //进行规约计算
                .reduce(new QhReduceAggregate(), new QhProcessWindowFunction());
        result.print();
        env.execute("reduce Demo");
    }

    /**
     * reduce 函数连续应用于
     */
    public static class QhReduceAggregate implements ReduceFunction<Tuple2<String, Long>> {
        /**
         * 对于两个结果进行规约
         *
         * @param value1 上一次规约之后的值
         * @param value2 新的值
         *
         * @return
         */
        @Override
        public Tuple2<String, Long> reduce(Tuple2<String, Long> value1, Tuple2<String, Long> value2) {
            Tuple2 maxvalue = value1.f1 > value2.f1 ? value1 : value2;
            // 求取最大值
            return maxvalue;
        }
    }

    private static class QhProcessWindowFunction extends ProcessWindowFunction<Tuple2<String, Long>, Tuple2<Long, Tuple2<String, Long>>, String, TimeWindow> {
        @Override
        public void process(String s, Context context, Iterable<Tuple2<String, Long>> elements, Collector<Tuple2<Long, Tuple2<String, Long>>> out) throws Exception {
            Tuple2<String, Long> max = elements.iterator().next();
            out.collect(new Tuple2<>(context.window().getEnd(), max));
        }
    }
}
