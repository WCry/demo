package com.zxp.demo.flink.stream.socketdemo;
/**
 * @since 3.0
 */

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;


public class StreamingJob {
    /**
     * 需要先启动 util中的Socket通信监听
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        // 创建流执行环境
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        //flink 作为Socket客户端监听 Server 端发出的消息 进行统计
        DataStream<String> text = env.socketTextStream("localhost", 9000);
        //对于当前的结果进行映射 成为新的流
        DataStream<Tuple2<String, Integer>> dataStream = text.flatMap((FlatMapFunction<String, Tuple2<String, Integer>>) (s, collector) -> {
            //自定义分割 和取出 需要的值 作为映射结果
            String[] tokens = s.toLowerCase().split("\\W+");
            for (String token : tokens) {
                if (token.length() > 0) {
                    collector.collect(new Tuple2<>(token, 1));
                }
            }
        })
                //通过key    进行分组
                .keyBy(0)
                //时间处理窗口 大小
                .timeWindow(Time.seconds(5))
                //进行求和操作  用户相加的字段
                .sum(1);
        //对于记过进行sink   sink 简单的输出
        dataStream.print();
        // execute program
        env.execute("Java WordCount from SocketTextStream Example");
    }
}

