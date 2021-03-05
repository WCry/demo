package com.zxp;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.api.java.function.VoidFunction;
import scala.Tuple2;

import java.util.Arrays;
import java.util.List;

/**
 * @author zhangxuepei
 * @since 3.0
 */
public class SparkWorldCountSortResult {
    //需要在环境变量中配置HADOOP_HOME路径
    private static String appName = "spark.demo";
    //调试环境必须设置成为local[*]， 提交到集群上在设置集群master
    private static String master = "local[*]";

    public static void main(String[] args) {
        //修改日志级别
        Logger.getLogger("org").setLevel(Level.ERROR);
        JavaSparkContext sc = null;
        try {
            //初始化 JavaSparkContext
            SparkConf conf = new SparkConf().setAppName(appName).setMaster(master);

            sc = new JavaSparkContext(conf);
            // 构造数据源
            List<String> data = Arrays.asList("a", "b", "a", "a", "c", "c", "f", "e");
            //并行化创建rdd 创建第一个RDD
            JavaRDD<String> rdd = sc.parallelize(data);
            //映射成为key和Value模式的 得到map之后的RDD
            JavaPairRDD<String, Integer> mapToPair = rdd.mapToPair((PairFunction<String, String, Integer>) s -> {
                Tuple2<String, Integer> tuple2 = new Tuple2<>(s, 1);
                return tuple2;
            });
            //按照key进行规约reduce处理
            JavaPairRDD<String, Integer> reduceByKey = mapToPair.reduceByKey((Function2<Integer, Integer, Integer>) (v1, v2) -> v1 + v2);
            //交换Value和Key的顺序 然后按照Value进行排序
            JavaPairRDD<Integer, String> value2Key= reduceByKey.mapToPair((PairFunction<Tuple2<String, Integer>,
                    Integer, String>) stringIntegerTuple2 -> stringIntegerTuple2.swap());
            //将Value和Key重新交换回来
            //按照降序进行排序
            JavaPairRDD<String, Integer> result= value2Key.sortByKey(true).mapToPair((PairFunction<Tuple2<Integer,
                    String>, String, Integer>) integerStringTuple2 -> integerStringTuple2.swap());
            //对于规约结果进行输出
            result.foreach((VoidFunction<Tuple2<String, Integer>>) stringIntegerTuple2 -> {
                stringIntegerTuple2.swap();
                System.out.println(stringIntegerTuple2);
            });
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sc != null) {
                sc.close();
            }
        }
    }
}
