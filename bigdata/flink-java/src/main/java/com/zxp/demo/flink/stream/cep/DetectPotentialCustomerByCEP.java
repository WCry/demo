package com.zxp.demo.flink.stream.cep;

import com.zxp.demo.flink.entry.CustomerEvent;
import com.zxp.demo.flink.util.EnvironmentUtil;
import org.apache.flink.cep.CEP;
import org.apache.flink.cep.PatternStream;
import org.apache.flink.cep.functions.PatternProcessFunction;
import org.apache.flink.cep.pattern.Pattern;
import org.apache.flink.cep.pattern.conditions.SimpleCondition;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.util.Collector;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author zhangxuepei
 * @since 3.0
 * DetectPotential 检测潜在客户通过CEP表达式
 */
public class DetectPotentialCustomerByCEP {
    public static void main(String[] args) throws Exception {
        String filePath = EnvironmentUtil.startDir + "\\testData\\customer.txt";
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);
        DataStreamSource<String> dataStreamSource = env.readTextFile(filePath);
        //首先过滤掉对象为空的
        KeyedStream<CustomerEvent, String> partitionedInput =
                dataStreamSource.filter(Objects::nonNull).map(s -> {
            // 输入的string，逗号分隔，第一个字段为用户名，第二个字段为事件类型
            String[] strings = s.split(",");
            if (strings.length != 2) {
                return null;
            }
            CustomerEvent event = new CustomerEvent();
            event.setName(strings[0]);
            event.setType(Integer.parseInt(strings[1]));
            System.out.println(event);
            return event;
        }).keyBy(CustomerEvent::getName);

        // 先点击浏览商品，然后将商品加入收藏
        Pattern<CustomerEvent, CustomerEvent> patternA =
                Pattern.<CustomerEvent>begin("firstly").where(new SimpleCondition<CustomerEvent>() {
            @Override
            public boolean filter(CustomerEvent event) {
                // 点击商品
                return event.getType() == 0;
            }
            //next操作 紧挨着
        }).next("and").where(new SimpleCondition<CustomerEvent>() {
            @Override
            public boolean filter(CustomerEvent event) {
                // 将商品加入收藏
                return event.getType() == 1;
            }
        });
        // 1分钟内点击浏览了商品3次。
        Pattern<CustomerEvent, ?> patternB =
                Pattern.<CustomerEvent>begin("start").where(new SimpleCondition<CustomerEvent>() {
                    @Override
                    public boolean filter(CustomerEvent event) {
                        // 浏览商品
                        return event.getType() == 0;
                    }
                }).timesOrMore(3).within(Time.minutes(1));

        // CEP用pattern将输入的时间事件流转化为复杂事件流
        PatternStream<CustomerEvent> patternStreamA = CEP.pattern(partitionedInput, patternA);
        PatternStream<CustomerEvent> patternStreamB = CEP.pattern(partitionedInput, patternB);

        DataStream<String> streamA = processPatternStream(patternStreamA, "收藏商品");
        DataStream<String> streamB = processPatternStream(patternStreamB, "连续浏览商品");

        // 最后两个复杂事件流进行合并
        streamA.union(streamB).print();

        env.execute("Flink Streaming Java API Skeleton");
    }

    public static DataStream<String> processPatternStream(PatternStream<CustomerEvent> patternStream, String tag) {
        return patternStream.process(new PatternProcessFunction<CustomerEvent, String>() {
            @Override
            public void processMatch(Map<String, List<CustomerEvent>> match, Context ctx, Collector<String> out) throws Exception {
                String name = null;
                for (Map.Entry<String, List<CustomerEvent>> entry : match.entrySet()) {
                    name = entry.getValue().get(0).getName();
                }
                out.collect(name + " 成为潜在客户 ," + tag);
            }
        });
    }

}
