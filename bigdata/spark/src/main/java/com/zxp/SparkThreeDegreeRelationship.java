package com.zxp;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.PairFlatMapFunction;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.storage.StorageLevel;
import scala.Tuple2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author zhangxuepei
 * @since 3.0
 */
public class SparkThreeDegreeRelationship {
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
            //构建一度双向关系
            JavaPairRDD<String, String> r1 = rdd.flatMapToPair(new PairFlatMapFunction<String, String, String>() {
                @Override
                public Iterator<Tuple2<String, String>> call(String t) throws Exception {
                    List<Tuple2<String, String>> list = new ArrayList();
                    String[] eachterm = t.split(",");
                    list.add(new Tuple2(eachterm[0], eachterm[0] + "," + eachterm[1] + "," + "deg1friend" + "," + eachterm[0] + "->" + eachterm[1]));
                    list.add(new Tuple2(eachterm[1], eachterm[1] + "," + eachterm[0] + "," + "deg1friend" + "," + eachterm[1] + "->" + eachterm[0]));
                    return list.iterator();
                }
            });
            r1.persist(StorageLevel.DISK_ONLY());
            //通过key进行分组
            JavaPairRDD<String, Iterable<String>> r2 = r1.groupByKey();
            //求解二度关系
            JavaPairRDD<String, String> r3 = r2.flatMapToPair((PairFlatMapFunction<Tuple2<String, Iterable<String>>, String, String>) t -> {
                //对于共同认识某一个key的人
                List<Tuple2<String, String>> list = new ArrayList();
                for (Iterator iter = t._2.iterator(); iter.hasNext(); ) {
                    String str1 = (String)iter.next();
                    String str1_1 = str1.split(",")[1];
                    list.add(new Tuple2(t._1+ "->" + str1_1,"deg1friend,"+t._1+ "->" + str1_1));
                    for (Iterator iter2 = t._2.iterator(); iter2.hasNext();) {
                        String str2 = (String)iter2.next();
                        String str2_1 = str2.split(",")[1];
                        if(!str1_1.equals(str2_1)){
                            list.add(new Tuple2(str1_1+ "->" + str2_1 ,"deg2friend,"+str1_1 + "->" + t._1 + "->" + str2_1));
                        }
                    }
                }
                return list.iterator();
            });
            //直接关系结果
            JavaPairRDD<String, String> r4 = r3.filter(new Function<Tuple2<String, String>, Boolean>() {
                @Override
                public Boolean call(Tuple2<String, String> v1) throws Exception {
                    //如果包含直接关系 表示是否接受
                    return v1._2.indexOf("deg1friend") > -1;
                }
            });
            //存储结果到磁盘当中
            r4.persist(StorageLevel.DISK_ONLY());
            //二度和一度  根据key减去一度关系
            JavaPairRDD<String, String> r5 = r3.subtractByKey(r4);
            //不进行去重 需要在这里需要进行分类 然后在重新构建二度双向关系
            JavaPairRDD<String, String> r6 = r5.flatMapToPair(new PairFlatMapFunction<Tuple2<String, String>, String, String>() {
                @Override
                public Iterator<Tuple2<String, String>> call(Tuple2<String, String> t) throws Exception {
                    List<Tuple2<String, String>> list = new ArrayList();
                    String t0 = t._1.split("->")[0];
                    String t1 = t._1.split("->")[1];
                    String t2_1 = t._2.split(",")[1];
                    list.add(new Tuple2(t0, t0 + "," + t1 + "," + "deg2friend" + "," + t2_1));
                    list.add(new Tuple2(t1, t1 + "," + t0 + "," + "deg2friend" + "," + t2_1));
                    return list.iterator();
                }
            });
            //初始和R6进行求和
            JavaPairRDD<String, String> r7 = r1.union(r6);
            //对于R7进行按照key进行排序
            JavaPairRDD<String, Iterable<String>> r8 = r7.groupByKey();

            //分析出三度关系
            JavaPairRDD<String, String> r9 = r8.flatMapToPair(new PairFlatMapFunction<Tuple2<String, Iterable<String>>, String, String>() {
                @Override
                public Iterator<Tuple2<String, String>> call(Tuple2<String, Iterable<String>> t) throws Exception {
                    List<Tuple2<String, String>> list = new ArrayList();
                    for (Iterator iter = t._2.iterator(); iter.hasNext(); ) {
                        String str1 = (String) iter.next();
                        String str1_0 = str1.split(",")[0];
                        String str1_1 = str1.split(",")[1];
                        String str1_2 = str1.split(",")[2];
                        String str1_3 = str1.split(",")[3];
                        for (Iterator iter2 = t._2.iterator(); iter2.hasNext(); ) {
                            String str2 = (String) iter2.next();
                            String str2_0 = str2.split(",")[0];
                            String str2_1 = str2.split(",")[1];
                            String str2_2 = str2.split(",")[2];
                            String str2_3 = str2.split(",")[3];
                            if (!str1_1.equals(str2_1) && str1_2.equals("deg2friend") && str2_2.
                                    equals("deg1friend") && !(str1_3.indexOf(str2_1) > -1) && (str1_3.split("->")[0].equals(str1_1)) && str1_0.equals(str2_0)) {
                                list.add(new Tuple2(str1_1 + "->" + str2_1, "deg3friend," + str1_3 + "->" + str2_1));
                            }
                        }
                    }
                    return list.iterator();
                }
            });
            //减去一度关系和二度关系， 得到三度关系
            JavaPairRDD<String, String> r10 = r9.subtractByKey(r4);

            System.out.println("线路走向：" + StringUtils.join(r10.collect(), ","));

            Map<String, Long> r11 = r10.countByKey();

            System.out.println(r11);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sc != null) {
                sc.close();
            }
        }
    }
}
