package com.zxp.demo.flink.counter;

/**
 * @author zhangxuepei
 * @since 3.0
 */
import org.apache.flink.api.common.JobExecutionResult;
import org.apache.flink.api.common.accumulators.IntCounter;
import org.apache.flink.api.common.functions.RichMapFunction;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;


public class CounterTest1 {
    public static void main(String[] args) throws Exception {
        //获取执行环境
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        //数据来源
        DataStream<String> source1 = env.fromElements("a", "b", "c");
        //operate
        source1.map(new RichMapFunction<String, String>() {
            //第一步：定义累加器
            IntCounter numLines = new IntCounter();
            @Override
            public void open(Configuration parameters) throws Exception {
                //第二步：注册累加器
                getRuntimeContext().addAccumulator("num-lines", numLines);
            }
            @Override
            public String map(String s) {
                //第三步：累加
                numLines.add(1);
                System.out.println("LocalValue:" + numLines.getLocalValue()+",线程id#"+Thread.currentThread().getId());
                return s;
            }

        }).setParallelism(1);
        //数据去向
        source1.print();
        //执行
        JobExecutionResult exe1 = env.execute("Flink CounterTest1 Demo");
        String total = exe1.getAccumulatorResult("num-lines").toString();
        System.out.println("total:" + total);
    }//main

}

