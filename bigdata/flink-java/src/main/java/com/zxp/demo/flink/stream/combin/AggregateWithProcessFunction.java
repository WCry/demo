package com.zxp.demo.flink.stream.combin;

import org.apache.flink.api.common.functions.AggregateFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.calcite.shaded.com.google.common.collect.Lists;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.windowing.ProcessWindowFunction;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;

import java.util.List;


/**
 * @author zhangxuepei
 * @since 3.0
 */
public class AggregateWithProcessFunction {
    public static void main(String[] args) throws Exception {
        List<Tuple2<String, Long>> source = Lists.newArrayList();
        source.add(new Tuple2<>("qh1", 88L));
        source.add(new Tuple2<>("qh1", 99L));
        source.add(new Tuple2<>("qh1", 100L));
        //获取处理流
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStream<Tuple2<String, Long>> dataStreamSource = env.fromCollection(source);
        SingleOutputStreamOperator<Tuple2<String, Double>> result =
                //进行分类
                dataStreamSource.keyBy(t -> t.f0).
                timeWindow(Time.seconds(1))
                        //聚合函数
                        .aggregate(new QhAverageAggregate(), new QhProcessWindowFunction());
        result.print();
        env.execute("q Demo");
        Thread.sleep(1000);
    }

    /**
     * 求 平均数的聚合函数
     */
    public static class QhAverageAggregate implements AggregateFunction<Tuple2<String, Long>, Tuple2<Long, Long>, Double> {
        /**
         * 聚合的初始化  创建累加器
         * @return
         */
        @Override
        public Tuple2<Long, Long> createAccumulator() {
            return new Tuple2<>(0L, 0L);
        }

        /**
         * 累加器和当前值
         * @param value
         * @param accumulator
         * @return
         */
        @Override
        public Tuple2<Long, Long> add(Tuple2<String, Long> value, Tuple2<Long, Long> accumulator) {
            return new Tuple2<>(accumulator.f0 + value.f1, +accumulator.f1 + 1);
        }

        /**
         * 获取最终结果     获取平均值
         * @param accumulator
         * @return
         */
        @Override
        public Double getResult(Tuple2<Long, Long> accumulator) {
            return ((double) accumulator.f0) / accumulator.f1;
        }

        /**
         * 合并前后两个对象
         * @param a
         * @param b
         * @return
         */
        @Override
        public Tuple2<Long, Long> merge(Tuple2<Long, Long> a, Tuple2<Long, Long> b) {
            return new Tuple2<>(a.f0 + b.f0, +a.f1 + b.f1);
        }
    }

    /**
     * 窗口函数
     */
    private static class QhProcessWindowFunction extends ProcessWindowFunction<Double,
            Tuple2<String, Double>, String, TimeWindow> {
        public void process(String key, Context context, Iterable<Double> averages, Collector<Tuple2<String, Double>> out) {
            Double average = averages.iterator().next();
            out.collect(new Tuple2<>(key, average));
        }
    }
}
