package com.zxp.demo.flink.stream.trigger;

import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.windowing.ProcessAllWindowFunction;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.util.Collector;

/**
 * @author zhangxuepei
 * @since 3.0
 */
public class TriggerDemo {
    //todo main方法执行
    public static void main(String[] args) {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStreamSource<String> streams = env.fromElements("aaa");
        streams.timeWindowAll(Time.seconds(10))
                .trigger(new CountTriggerWithTimeout(1000, TimeCharacteristic.ProcessingTime)
                )
                //todo 做业务操作
                .process(new ProcessAllWindowFunction() {
                    @Override
                    public void process(Context context, Iterable iterable, Collector collector) throws Exception {

                    }
                })
                //todo 输出
                .print()
                .name("Xxx");
        try {
            env.execute("aaa");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
