package com.zxp.demo.flink.dataset;

import org.apache.flink.api.common.functions.CoGroupFunction;
import org.apache.flink.api.common.functions.util.ListCollector;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangxuepei
 * @since 3.0
 */
public class DatasetCoGroup {
    public static void main(String[] args) throws Exception {
        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
        DataSet<Tuple2<Long, String>> source1 = env.fromElements(
                Tuple2.of(1L, "xiaoming"),
                Tuple2.of(2L, "xiaowang"));
        DataSet<Tuple2<Long, String>> source2 = env.fromElements(
                Tuple2.of(2L, "xiaoli"),
                Tuple2.of(1L, "shinelon"),
                Tuple2.of(3L, "hhhhhh"));
        //将不同的数据流中  相同的key 划分到统一个组当中  分到同一个 区当中需要下一步 做什么交给with去操作
        source1.coGroup(source2)
                .where(0).equalTo(0)
                .with((CoGroupFunction<Tuple2<Long, String>, Tuple2<Long, String>, Map<Long, String>>) (iterable, iterable1, collector) -> {
                    Map<Long, String> map = new HashMap<Long, String>();
                    for (Tuple2<Long, String> tuple : iterable) {
                        String str = map.get(tuple.f0);
                        if (str == null) {
                            map.put(tuple.f0, tuple.f1);
                        } else {
                            if (!str.equals(tuple.f1))
                                map.put(tuple.f0, str + " " + tuple.f1);
                        }
                    }
                    for (Tuple2<Long, String> tuple : iterable1) {
                        String str = map.get(tuple.f0);
                        if (str == null) {
                            map.put(tuple.f0, tuple.f1);
                        } else {
                            if (!str.equals(tuple.f1))
                                map.put(tuple.f0, str + " " + tuple.f1);
                        }
                    }
                    collector.collect(map);
                }).returns(Types.MAP(Types.LONG,Types.STRING)).print();
    }
}
