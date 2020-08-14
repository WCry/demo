package com.zxp;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFlatMapFunction;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.api.java.function.VoidFunction;
import scala.Tuple2;
import sun.applet.AppletResourceLoader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author zhangxuepei
 * @since 3.0
 */
public class SparkTwoDegreeRelationship {
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

            //并行化创建rdd 创建第一个RDD
            JavaRDD<String> rdd = sc.textFile("config/DegreedRelationship.txt");
            //对于数据进行拆分
            JavaPairRDD<String, String> split = rdd.flatMapToPair((PairFlatMapFunction<String, String, String>) s -> {
                String[] AB = s.split(",");
                List<Tuple2<String, String>> abList = new ArrayList<>(2);
                //以哪个为key的对象放在前面
                abList.add(new Tuple2(AB[0], AB[1]));
                abList.add(new Tuple2(AB[1], AB[0]));
                return abList.iterator();
            });
            //通过key进行分组
            JavaPairRDD<String, Iterable<String>> abGroupByKey = split.groupByKey();
            //求解二度关系
            JavaPairRDD<String, String> r3 = abGroupByKey.flatMapToPair((PairFlatMapFunction<Tuple2<String, Iterable<String>>, String, String>) t -> {
                //对于共同认识某一个key的人
                List<Tuple2<String, String>> list = new ArrayList();
                for (Iterator iter = t._2.iterator(); iter.hasNext(); ) {
                    String str1_1 = (String) iter.next();
                    //标识关系
                    list.add(new Tuple2(t._1+ "->" + str1_1, "deg1,"+t._1+ "->" + str1_1));
                    for (Iterator iter2 = t._2.iterator(); iter2.hasNext(); ) {
                        String str2_1 = (String) iter2.next();
                        if (!str1_1.equals(str2_1)) {
                            list.add(new Tuple2(str1_1 + "->" + str2_1, "deg2," + str1_1 + "->" + t._1 + "->" + str2_1));
                        }
                    }
                }
                return list.iterator();
            });
            //直接关系结果
            JavaPairRDD<String, String> r4 = r3.filter(new Function<Tuple2<String,String>,Boolean>(){
                @Override
                public Boolean call(Tuple2<String, String> v1) throws Exception {
                    //如果包含直接关系 表示是否接受
                    return v1._2.indexOf("deg1")>-1;
                }
            });
            //二度和一度  根据key减去一度关系
            JavaPairRDD<String, String> r5=r3.subtractByKey(r4);
            System.out.println("二度关系信息：");
            //链接二度关系 进行输出
            r5.foreach(new VoidFunction<Tuple2<String, String>>() {
                @Override
                public void call(Tuple2<String, String> stringStringTuple2) throws Exception {
                    System.out.println(stringStringTuple2);
                }
            });
            System.out.println("二度关系及比重(中间共同认识好友数量)：");
            Map<String, Long> r6 = r3.countByKey();
            r6.forEach((key,value)->{System.out.println(key+"权重："+value);});
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sc != null) {
                sc.close();
            }
        }
    }
}
