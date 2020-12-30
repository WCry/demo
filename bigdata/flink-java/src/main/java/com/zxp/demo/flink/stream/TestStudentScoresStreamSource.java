package com.zxp.demo.flink.stream;

import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.functions.source.SourceFunction;

import java.util.Random;

/**
 * @author zhangxuepei
 * @since 3.0
 */
public class TestStudentScoresStreamSource implements SourceFunction<Tuple2<String, Integer>> {
    private volatile Boolean isRunning = true;
    @Override
    public void run(SourceContext<Tuple2<String, Integer>> sourceContext) throws Exception {
        int i = 0;
        String[] names = new String[]{"张三", "李四", "王五", "麻六", "刘七"};
        Random random = new Random();
        while (isRunning) {
            sourceContext.collect(new Tuple2<>(names[i % 4], random.nextInt(100)));
            Thread.sleep(10);
            i++;
        }
    }
    @Override
    public void cancel() {
        isRunning = false;
    }
}
