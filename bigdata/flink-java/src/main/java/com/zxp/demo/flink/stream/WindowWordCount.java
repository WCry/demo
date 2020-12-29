package com.zxp.demo.flink.stream;

import com.zxp.demo.flink.util.SourceFromFile;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.CheckpointingMode;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.util.Collector;

/**
 * @author zhangxuepei
 * @since 3.0
 */
public class WindowWordCount {
    public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.enableCheckpointing(5000, CheckpointingMode.AT_LEAST_ONCE);
        DataStreamSource<String> dataStream = env.addSource(new SourceFromFile()).setParallelism(1);
        dataStream.flatMap(new FlatMapFunction<String, Tuple2<String, Integer>>() {
                    @Override
                    public void flatMap(String value, Collector<Tuple2<String, Integer>> out) throws Exception {
                        String[] arr = value.split(",");
                        for (String item : arr) {
                            out.collect(new Tuple2<>(item, 1));
                        }
                    }
                })
                .keyBy(0)
                .timeWindow(Time.seconds(1))
                .sum(1)
                .print();

        env.execute("WindowWordCount");
    }
}
