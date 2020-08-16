package com.zxp;


import org.apache.calcite.adapter.enumerable.RexImpTable;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.graphx.Edge;
import org.apache.spark.graphx.GraphLoader;
import org.apache.spark.graphx.VertexRDD;
import org.apache.spark.graphx.Graph;
import org.apache.spark.graphx.VertexRDD;
import org.apache.spark.graphx.impl.EdgeRDDImpl;
import org.apache.spark.graphx.impl.VertexRDDImpl;
import org.apache.spark.storage.StorageLevel;
import scala.Tuple2;
import scala.reflect.ClassTag;
import scala.reflect.ClassTag$;

import java.util.ArrayList;
import java.util.Arrays;
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
            List<Tuple2<Long, String>> vertices=new ArrayList<>();
            vertices.add(new Tuple2<>(1L, "Ann"));
            vertices.add(new Tuple2<>(2L, "Bill"));
            vertices.add(new Tuple2<>(3L, "Charles"));
            vertices.add(new Tuple2<>(4L, "Diane"));
            vertices.add(new Tuple2<>(5L, "Went to gym this morning"));
            vertices.add(new Tuple2<>(6L, "Ann dddd"));
            JavaRDD<Tuple2<Long, String>> myVertices = sc.parallelize(vertices);
            //边
            List<Edge> edgeArray = new ArrayList<>();
            edgeArray.add(new Edge(2L, 1L, 5));
            edgeArray.add(new Edge(2L, 4L, 2));
            edgeArray.add(new Edge(3L, 2L, 7));
            edgeArray.add(new Edge(3L, 6L, 3));
            edgeArray.add(new Edge(4L, 1L, 1));
            edgeArray.add(new Edge(5L, 2L, 3));
            edgeArray.add(new Edge(5L, 3L, 8));
            edgeArray.add(new Edge(5L, 6L, 8));
            JavaRDD<Edge> myEdges = sc.parallelize(edgeArray);


            Graph<String, String> myGraph = Graph.apply(myVertices, myEdges, "", StorageLevel.MEMORY_ONLY());

            GraphLoader.edgeListFile() Graph graph;
            System.out.println("年龄大于20的人");
            graph.vertices().filter(v -> v.._2 > 20).collect.foreach {
                v =>println(s"${v._2._1} is ${v._2._2}")

            } println("图中属性大于3的边") graph.edges.filter(e = > e.attr > 3).
            collect.foreach(e = > println(s"${e.srcId} to ${e.dstId} att ${e.attr}"))
            println("triplet操作")
            for (t< -graph.triplets.filter(s = > s.attr > 3).collect){
                println(s"${t.srcAttr._1} likes ${t.dstAttr._1}")
            }
        } catch (Exception ex) {

        }


    }
}
