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
import org.apache.flink.streaming.api.windowing.assigners.DynamicEventTimeSessionWindows;
import org.apache.flink.streaming.api.windowing.assigners.GlobalWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;

/**
 * @author zhangxuepei
 * @since 3.0
 * 自定义触发器
 * 实际上是自定义窗口
 * https://www.cnblogs.com/qiu-hua/p/13782398.html
 * https://www.jianshu.com/p/a883262241ef
 */
public class TriggerDemo {

    public static void main(String[] args) {
        //获取处理流
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setStreamTimeCharacteristic(TimeCharacteristic.IngestionTime);
      //  TimeCharacteristic.IngestionTime
        DataStream<Tuple2<String, Integer>> dataStreamSource = env.addSource(new TestStudentScoresStreamSource());
        dataStreamSource.keyBy(t -> t.f0).
                //首先创建窗口 计算
                window(GlobalWindows.create())
                //修改当前的时间窗口触发器 实际自定义trigger 还需要了解
                //自定义trigger的触发条件和窗口 新建窗口的时间对上
                //这里 实现一个 计数窗口计算
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
