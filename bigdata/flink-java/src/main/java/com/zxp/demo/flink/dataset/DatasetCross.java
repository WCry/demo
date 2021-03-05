package com.zxp.demo.flink.dataset;

import com.zxp.demo.flink.entry.PlayerData;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.table.api.java.BatchTableEnvironment;

import java.util.ArrayList;

/**
 * @author zhangxuepei
 * @since 3.0
 */
public class DatasetCross {
    public static void main(String[] args) throws Exception {
        //1\. 获取上下文环境 table的环境
        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
        DataSource dataSource1= env.fromCollection(new ArrayList<Integer>(){{
            add(1);
            add(2);
            add(3);
        }});
        DataSource dataSource2= env.fromCollection(new ArrayList<String>(){{
            add("beijing");
            add("shanghai");
            add("hangzhou");
        }});
        //Cross构建两个数据集之间的笛卡尔集所有记录 不需要通过Key进行关联  只是简单的D卡尔集合
        dataSource1.cross(dataSource2).print();
    }
}
