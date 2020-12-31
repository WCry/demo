package com.zxp.demo.flink.stream.trigger;

import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.functions.windowing.ProcessWindowFunction;
import org.apache.flink.streaming.api.windowing.windows.GlobalWindow;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;

/**
 * @author zhangxuepei
 * @since 3.0
 */


public class TestProcessWindowsFunction extends ProcessWindowFunction<Tuple2<String, Integer>, Tuple2<String, Double>,
        String, GlobalWindow> {

    @Override
    public void process(String key, Context context, Iterable<Tuple2<String, Integer>> elements,
                        Collector<Tuple2<String, Double>> out) throws Exception {
        int i=0;
        for (Tuple2<String, Integer> element : elements) {
            i++;
        }
        Double average=10.0;
        out.collect(new Tuple2<>(key, new Double(i)));
    }
}