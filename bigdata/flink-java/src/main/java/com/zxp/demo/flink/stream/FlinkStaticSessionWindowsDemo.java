package com.zxp.demo.flink.stream;


import com.zxp.demo.flink.util.StreamDataSource;
import org.apache.flink.api.common.functions.ReduceFunction;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.assigners.EventTimeSessionWindows;
import org.apache.flink.streaming.api.windowing.time.Time;

/**
 * @author zhangxuepei
 * @since 3.0
 */
public class FlinkStaticSessionWindowsDemo {

    public static void main(String[] args) throws Exception {
        //水位线 允许延迟多少时间
        long delay = 5000L;
        //Session 的窗口间隙  当session的间隔大于设置阈值  人为一个窗口结束
        long windowGap = 100L;

        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        //设置流 按照事件发生的时间进行比较处理
        env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);
        //设置并行度
        env.setParallelism(1);

        // 设置数据源
        DataStream<Tuple3<String, String, Long>> source = env.addSource(new StreamDataSource()).name("Demo Source");
        // 设置水位线
//        DataStream<Tuple3<String, String, Long>> stream = source.assignTimestampsAndWatermarks(
//                new BoundedOutOfOrdernessTimestampExtractor<Tuple3<String, String, Long>>
//                        (Time.milliseconds(delay)) {
//            @Override
//            public long extractTimestamp(Tuple3<String, String, Long> element) {
//                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
//                System.out.println(element.f0 + "\t" + element.f1 + " watermark -> " +
//                        format.format(getCurrentWatermark().getTimestamp()) +
//                        " timestamp -> " + format.format(element.f2));
//                //水位线 是当前对象的是时间
//                return element.f2;
//            }
//        });

        // 窗口聚合
        source.keyBy(0).window(EventTimeSessionWindows.withGap(Time.milliseconds(windowGap))).
                reduce(new ReduceFunction<Tuple3<String, String, Long>>() {
            @Override
            public Tuple3<String, String, Long> reduce(Tuple3<String, String, Long> value1,
                                                       Tuple3<String, String, Long> value2) throws Exception {
                return Tuple3.of(value1.f0, value1.f1 + "" + value2.f1, 1L);
            }
        }).print();

        env.execute("TimeWindowDemo");
    }
}
