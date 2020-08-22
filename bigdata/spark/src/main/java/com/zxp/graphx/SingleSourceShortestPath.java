package com.zxp.graphx;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.graphx.Edge;
import org.apache.spark.graphx.EdgeDirection;
import org.apache.spark.graphx.EdgeTriplet;
import org.apache.spark.graphx.Graph;
import org.apache.spark.graphx.Pregel;
import org.apache.spark.graphx.util.GraphGenerators;
import scala.Predef.$eq$colon$eq;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.collection.JavaConverters;
import scala.reflect.ClassTag;
import scala.reflect.ClassTag$;
import scala.runtime.AbstractFunction1;
import scala.runtime.AbstractFunction2;
import scala.runtime.AbstractFunction3;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

//报错 不影响使用

/**
 * @author zhangxuepei
 * @since 3.0
 */
public class SingleSourceShortestPath {

    private static final ClassTag<Double> tagDouble = ClassTag$.MODULE$.apply(Double.class);
    private static final $eq$colon$eq eqDouble = new $eq$colon$eq() {
        @Override
        public Double apply(Object arg0) {
            return Double.parseDouble(arg0.toString());
        }

        ;
    };
    //需要在环境变量中配置HADOOP_HOME路径
    private static String appName = "spark.demo";
    //调试环境必须设置成为local[*]， 提交到集群上在设置集群master
    private static String master = "local[*]";

    public static void main(String[] args) {

        //修改日志级别
        Logger.getLogger("org").setLevel(Level.ERROR);
        //初始化 JavaSparkContext
        SparkConf conf = new SparkConf().setAppName(appName).setMaster(master);
        SparkContext sparkContext=new SparkContext(conf);
       // JavaSparkContext sc =new JavaSparkContext(conf);
        //产生一百个随机顶点的图
        Graph<Object, Double> shortGraph = GraphGenerators.logNormalGraph(sparkContext, 100, 1, 4.0, 1.3, -1).
                mapEdges(new SerializableFunction1<Edge<Object>, Double>() {
                    @Override
                    public Double apply(Edge<Object> a) {
                        return Double.parseDouble(a.attr().toString());
                    }

                }, tagDouble);
        //分析起点
        Integer verteId = 42;
        //通过操作顶点映射成为一个新的图 初始化顶点  起点设置成为0
        Graph<Double, Double> initalGraph = shortGraph.mapVertices(new SerializableFunction2<Object, Object, Double>() {
            @Override
            public Double apply(Object a, Object b) {
                if (Double.parseDouble(a.toString()) == verteId)
                    b = 0.0;
                else
                    b = Double.POSITIVE_INFINITY;
                return (Double) b;
            }

        }, tagDouble, eqDouble);

        Pregel.apply(initalGraph, Double.POSITIVE_INFINITY, Integer.MAX_VALUE, EdgeDirection.Out(),
                new SerializableFunction3<Object, Double, Double, Double>() {
                    @Override
                    public Double apply(Object a, Double b, Double c) {
                        System.out.println("b before" + b);
                        //获取BC中最小值
                        b = Math.min(b, c);
                        System.out.println("b after" + b);
                        return b;
                    }
                },
                new SerializableFunction1<EdgeTriplet<Double, Double>, scala.collection.Iterator<scala.Tuple2<Object, Double>>>() {

                    /**
                     *  public Iterator<Tuple2<Object, Integer>> apply(EdgeTriplet<Integer, Boolean> et) {
                     System.out.println(et.srcId()+" ---> "+et.dstId()+" with: "+et.srcAttr()+" ---> "+et.dstId());
                     if (et.srcAttr() > et.dstAttr()) {
                     return JavaConverters.asScalaIteratorConverter(Arrays.asList(et.toTuple()._1()).iterator()).asScala();
                     }else{
                     return JavaConverters.asScalaIteratorConverter(new ArrayList<Tuple2<Object, Integer>>().iterator()).asScala();
                     }
                     }
                     */
                    @Override
                    public Iterator<Tuple2<Object, Double>> apply(EdgeTriplet<Double, Double> a) {
                        // TODO Auto-generated method stub
                        System.out.println("SrcId" + a.srcId() + "SrcAttr" + a.srcAttr() + "EdgeAttr" + a.attr() + "dstId" + a.dstId() + "dstAttr" + a.dstAttr());
                        if (a.srcAttr() + a.attr() < a.dstAttr()) {
                            return JavaConverters.asScalaIteratorConverter(Arrays.asList(new Tuple2<Object, Double>(a.srcId(), a.srcAttr() + a.attr())).iterator()).asScala();
                        }
                        return JavaConverters.asScalaIteratorConverter(new ArrayList<Tuple2<Object, Double>>().iterator()).asScala();
                    }
                },
                // scala.Function2<A,A,A> mergeMsg,
                new SerializableFunction2<Double, Double, Double>() {
                    @Override
                    public Double apply(Double a, Double b) {

                        return Math.min(a, b);
                    }
                }, tagDouble, tagDouble, tagDouble);
        //.vertices().toJavaRDD().foreach(f->System.out.println("Show the generated graph"+f));
        sparkContext.stop();
    }

    static abstract class SerializableFunction1<T1, R> extends AbstractFunction1<T1, R> implements Serializable {

    }

    static abstract class SerializableFunction2<T1, T2, R> extends AbstractFunction2<T1, T2, R> implements Serializable {

    }

    static abstract class SerializableFunction3<T1, T2, T3, R> extends AbstractFunction3<T1, T2, T3, R> implements Serializable {

    }
}

