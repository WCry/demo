package com.zxp.demo.flink.stream.trigger;

import com.zxp.demo.flink.stream.TestStudentScoresStreamSource;
import com.zxp.demo.flink.stream.process.TestProcessWinFunctionOnWindow;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.windowing.ProcessAllWindowFunction;
import org.apache.flink.streaming.api.functions.windowing.ProcessWindowFunction;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;

/**
 * @author zhangxuepei
 * @since 3.0
 * 自定义触发器
 * 实际上是自定义窗口
 * https://www.cnblogs.com/qiu-hua/p/13782398.html
 */
public class TriggerDemo {

    public static void main(String[] args) {
        //获取处理流
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStream<Tuple2<String, Integer>> dataStreamSource = env.addSource(new TestStudentScoresStreamSource());
        dataStreamSource.keyBy(t -> t.f0)
                //时间窗口 默认的按照处理时间进行 触发
                .timeWindow(Time.milliseconds(5))
                //修改当前的时间窗口触发器 实际自定义trigger 还需要了解
                .trigger(CustomProcessingTimeTrigger.create())
                .process(new TestProcessWindowsFunction()).print();
        try {
            //设置执行器名称
            env.execute("aaa");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
