package com.zxp.graphx;


import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.graphx.Edge;
import org.apache.spark.graphx.Graph;
import org.apache.spark.graphx.VertexRDD;
import org.apache.spark.rdd.RDD;
import org.apache.spark.storage.StorageLevel;
import scala.Tuple2;
import scala.reflect.ClassTag;
import scala.reflect.ClassTag$;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangxuepei
 * @since 3.0
 * Spark 的图计算使用
 * 关于图计算在JAVA中的使用
 * https://github.com/Lynn628/GraphxExampleInJava/blob/master/GraphxInJava/src/main/java/SparkIn/simpleSpark/EdgeCount.java
 */
public class SparkGraph {
    //需要在环境变量中配置HADOOP_HOME路径
    private static String appName = "spark.demo";
    //调试环境必须设置成为local[*]， 提交到集群上在设置集群master
    private static String master = "local[*]";
    //Must explicitly provide for implicit Scala parameters in various
    //必须用显示的方式设置Scala的隐含的参数 主要是JAVA和scala之间转换使用和算法没有关系
    private static final ClassTag<Integer> tagInteger = ClassTag$.MODULE$.apply(Integer.class);
    private static final ClassTag<String> tagString = ClassTag$.MODULE$.apply(String.class);
    private static final ClassTag<Object> tagObject = ClassTag$.MODULE$.apply(Object.class);
    public static void main(String[] args) {

        //修改日志级别
        Logger.getLogger("org").setLevel(Level.ERROR);
        JavaSparkContext sc = null;
        try {
            //初始化 JavaSparkContext
            SparkConf conf = new SparkConf().setAppName(appName).setMaster(master);
            // conf.setSparkHome("D:\\hadoop-2.9.2\\bin");
            sc = new JavaSparkContext(conf);
            //这里必须是Object类型不能直接写成Long类型
            //构建顶点
            List<Tuple2<Object, String>> vertices=new ArrayList<>();
            vertices.add(new Tuple2<>(1L, "Ann"));
            vertices.add(new Tuple2<>(2L, "Bill"));
            vertices.add(new Tuple2<>(3L, "Charles"));
            vertices.add(new Tuple2<>(4L, "Diane"));
            vertices.add(new Tuple2<>(5L, "Went to gym this morning"));
            vertices.add(new Tuple2<>(6L, "Ann dddd"));
            JavaRDD<Tuple2<Object, String>> myVertices = sc.parallelize(vertices);
            //边
            List<Edge<String>> edgeArray = new ArrayList<>();
            edgeArray.add(new Edge(2L, 1L, 5));
            edgeArray.add(new Edge(2L, 4L, 2));
            edgeArray.add(new Edge(3L, 2L, 7));
            edgeArray.add(new Edge(3L, 6L, 3));
            edgeArray.add(new Edge(4L, 1L, 1));
            edgeArray.add(new Edge(5L, 2L, 3));
            edgeArray.add(new Edge(5L, 3L, 8));
            edgeArray.add(new Edge(5L, 6L, 8));
            JavaRDD<Edge<String>> myEdges = sc.parallelize(edgeArray);
            Graph<String, String> myGraph = Graph.apply(myVertices.rdd(),
                    myEdges.rdd(), "", StorageLevel.MEMORY_ONLY(),
                    StorageLevel.MEMORY_ONLY(), tagString, tagString);


        } catch (Exception ex) {

        }
    }
    //将JavaRDD与JavaPairRDD进行转换
    private static <T> JavaPairRDD<Object,T> toJavaPairRDD(VertexRDD<T> v, ClassTag<T> tagT) {
        return new JavaPairRDD<Object,T>((RDD<Tuple2<Object,T>>)v,tagObject, tagT);
    }
}
