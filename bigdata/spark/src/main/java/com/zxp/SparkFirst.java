package com.zxp;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;

import java.util.Arrays;
import java.util.List;

/**
 * @author zhangxuepei
 * @since 3.0
 */
public class SparkFirst {
    //需要在环境变量中配置HADOOP_HOME路径
    private static String appName = "spark.demo";
    //调试环境必须设置成为local[*]， 提交到集群上在设置集群master
    private static String master = "local[*]";

    public static void main(String[] args) {
        JavaSparkContext sc = null;
        try {
            //初始化 JavaSparkContext
            SparkConf conf = new SparkConf().setAppName(appName).setMaster(master);
           // conf.setSparkHome("D:\\hadoop-2.9.2\\bin");
            sc = new JavaSparkContext(conf);
            // 构造数据源
            List<Integer> data = Arrays.asList(1, 2, 3, 4, 5);
            //并行化创建rdd
            JavaRDD<Integer> rdd = sc.parallelize(data);
            //map && reduce
            Integer result = rdd.map(new Function<Integer, Integer>() {
                public Integer call(Integer integer) throws Exception {
                    //将数据映射成为一个简单的key类型
                    //也可以将类型映射成为key 和Value类型 rdd.mapPartitions()
                    return integer;
                }
            }).reduce(new Function2<Integer, Integer, Integer>() {
                public Integer call(Integer o, Integer o2) throws Exception {
                    //上一个循环的旧值 0
                    //当前需要计算的值
                    return o + o2;
                }
            });
            System.out.println("执行结果：" + result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sc != null) {
                sc.close();
            }
        }
    }
}
