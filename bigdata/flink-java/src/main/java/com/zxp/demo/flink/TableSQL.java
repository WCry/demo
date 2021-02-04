package com.zxp.demo.flink;

import com.zxp.demo.flink.entry.PlayerData;
import com.zxp.demo.flink.entry.Result;
import org.apache.flink.api.common.functions.MapFunction;

//批处理是Scala提供的操作
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.DataSet;
//需要引入 Flink Table 依赖  进行批处理
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.java.BatchTableEnvironment;

/**
 * @author zhangxuepei
 * @since 3.0
 */
public class TableSQL {

    public static void main(String[] args) throws Exception {
        //1\. 获取上下文环境 table的环境
        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
        BatchTableEnvironment tableEnv = BatchTableEnvironment.create(env);

        //2\. 读取score.csv
        DataSet<String> input = env.readTextFile("testData/score.csv");
        input.print();

        DataSet<PlayerData> topInput = input.map((MapFunction<String, PlayerData>) s -> {
            String[] split = s.split(",");
            return new PlayerData(String.valueOf(split[0]),
                    String.valueOf(split[1]),
                    String.valueOf(split[2]),
                    Integer.valueOf(split[3]),
                    Double.valueOf(split[4]),
                    Double.valueOf(split[5]),
                    Double.valueOf(split[6]),
                    Double.valueOf(split[7]),
                    Double.valueOf(split[8])
            );
        });
        //3\. 注册成内存表
        Table topScore = tableEnv.fromDataSet(topInput);
        tableEnv.registerTable("score", topScore);

        //4\. 编写sql 然后提交执行
        //select player, count(season) as num from score group by player order by num desc;
        Table queryResult = tableEnv.sqlQuery("select player, count(season) as num from score group by player "
                + "order by num desc limit 3");

        //5\. 结果进行打印
        DataSet<Result> result = tableEnv.toDataSet(queryResult, Result.class);
        result.print();
    }
}
