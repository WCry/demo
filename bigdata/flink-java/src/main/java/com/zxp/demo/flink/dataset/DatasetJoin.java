package com.zxp.demo.flink.dataset;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.tuple.Tuple3;

import java.util.ArrayList;

/**
 * @author zhangxuepei
 * @since 3.0
 */
public class DatasetJoin {
    public static void main(String[] args) throws Exception {
        //1\. 获取上下文环境 table的环境
        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
        DataSet<Tuple2<Integer, String>> dataSource1 = env.fromCollection(new ArrayList<Tuple2<Integer, String>>() {{
            add(new Tuple2<>(1, "ruoze"));
            add(new Tuple2<>(2, "jepson"));
            add(new Tuple2<>(3, "xingxing"));
            add(new Tuple2<>(4, "luohdde"));
        }});
        DataSet<Tuple2<Integer, String>> dataSource2 = env.fromCollection(new ArrayList<Tuple2<Integer, String>>() {{
            add(new Tuple2<>(1, "beijing"));
            add(new Tuple2<>(2, "shanghai"));
            add(new Tuple2<>(3, "hangzhou"));
            add(new Tuple2<>(3, "zhengzhou"));
        }});
        //join 使用左右都存在的全连接
        dataSource1.join(dataSource2).
                where((KeySelector<Tuple2<Integer, String>, Integer>) value -> value.f0).
                equalTo((KeySelector<Tuple2<Integer, String>, Integer>) value -> value.f0).
                map((MapFunction<Tuple2<Tuple2<Integer, String>, Tuple2<Integer, String>>, Tuple3<Integer, String,
                        String>>) value -> new Tuple3<>(value.f0.f0, value.f0.f1, value.f1.f1)).
                returns(Types.TUPLE(Types.INT,Types.STRING,Types.STRING)).print();
    }
}
