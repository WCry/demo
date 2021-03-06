package com.zxp.graphx;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.graphx.Edge;
import org.apache.spark.graphx.EdgeContext;
import org.apache.spark.graphx.Graph;
import org.apache.spark.graphx.TripletFields;
import org.apache.spark.graphx.VertexRDD;
import org.apache.spark.sql.sources.In;
import org.apache.spark.storage.StorageLevel;
import scala.Tuple2;
import scala.reflect.ClassTag;
import scala.reflect.ClassTag$;
import scala.runtime.AbstractFunction1;
import scala.runtime.AbstractFunction2;
import scala.runtime.BoxedUnit;

import java.io.Serializable;
import java.util.Arrays;

/**
 * @author zhangxuepei
 * @since 3.0
 * Spark 图分析 计算当前节点到 根节点的最远距离
 */

public class MaxDistanceToRootJoin {
    //Must explicitly provide for implicit Scala parameters in various function calls
    //classTag<T>保存了被泛型擦出后的原始类型T，提供给运行时的
    private static final ClassTag<Integer> tagInteger = ClassTag$.MODULE$.apply(Integer.class);
    private static final ClassTag<String> tagString = ClassTag$.MODULE$.apply(String.class);
    private static final ClassTag<Object> tagObject = ClassTag$.MODULE$.apply(Object.class);
    //sendMsg  BoxedUnit就是JAVA中的Void
    private static final SerializableFunction1<EdgeContext<Integer, String, Integer>, BoxedUnit> sendMsg = new SerializableFunction1<EdgeContext<Integer, String, Integer>, BoxedUnit>() {
        public BoxedUnit apply(EdgeContext<Integer, String, Integer> ec) {
            ec.sendToDst(ec.srcAttr() + 1);
            return BoxedUnit.UNIT;
        }
    };
    //mergeMsg
    private static final SerializableFunction2<Integer, Integer, Integer> mergeMsg = new SerializableFunction2<Integer, Integer, Integer>() {
        public Integer apply(Integer a, Integer b) {
            return Math.max(a, b);
        }
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
        JavaSparkContext sc = new JavaSparkContext(conf);

        JavaRDD<Tuple2<Object, String>> myVertices = sc.parallelize(Arrays.asList(new Tuple2<>(1L, "Ann"), new Tuple2<>(2L, "Bill"), new Tuple2<>(3L, "Charles"), new Tuple2<>(4L, "Diane"), new Tuple2<>(5L, "Diane2")));

        JavaRDD<Edge<String>> myEdges = sc.parallelize(Arrays.asList(new Edge<>(1L, 2L, "is-friends-with"), new Edge<>(2L, 3l, "is-friends-with"), new Edge<>(3L, 4L, "is-friends-with"), new Edge<>(4L, 5L, "Likes-status"), new Edge<>(3L, 5L, "Wrote-status")));

        Graph<String, String> myGraph = Graph.apply(myVertices.rdd(), myEdges.rdd(), "", StorageLevel.MEMORY_ONLY(), StorageLevel.MEMORY_ONLY(), tagString, tagString);
        //初始化所有节点的属性是0
        Graph<Integer, String> initialGraph = myGraph.mapVertices(new SerializableFunction2<Object, String, Integer>() {
            public Integer apply(Object o, String s) {return 0;}
        }, tagInteger, null);
        Graph<Integer, String> maxDistance= propagateEdgeCount(initialGraph);

        JavaPairRDD<Object,Integer> vertexJavaRDD= toJavaPairRDD( maxDistance.vertices(), tagInteger);
        vertexJavaRDD.foreach((VoidFunction<Tuple2<Object, Integer>>) objectIntegerTuple2 -> {
            //输出节点的ID和节点到达根节点的距离
            System.out.println(objectIntegerTuple2._1 +":"+objectIntegerTuple2._2);
        });
        sc.stop();
    }

    private static <T> JavaPairRDD<Object, T> toJavaPairRDD(VertexRDD<T> v, ClassTag<T> tagT) {
        return new JavaPairRDD<>(v, tagObject, tagT);

    }

    /**
     * 传播边的数量
     *
     * @param g
     *
     * @return
     */
    private static Graph<Integer, String> propagateEdgeCount(Graph<Integer, String> g) {
        //aggregateMessages,
        // 聚合函数采用以边为依据进行遍历，sendMessage
        // 以边的边为操作可以向边的起点和终点发送消息
        //mergeMsg 对于每一个顶点收到的信息进行汇总
        //TripletFields 三元字段数组
        VertexRDD<Integer> verts = g.aggregateMessages(sendMsg, mergeMsg, TripletFields.All, tagInteger);
        //重新构建一个图
        Graph<Integer, String> g2 = Graph.apply(verts, g.edges(), 0, StorageLevel.MEMORY_ONLY(), StorageLevel.MEMORY_ONLY(), tagInteger, tagString);
        //通过顶点作为遍历依据，进行join操作
        int check = toJavaPairRDD(g2.vertices(), tagInteger).join(toJavaPairRDD(g.vertices(), tagInteger)).
                map((Function<Tuple2<Object, Tuple2<Integer, Integer>>, Integer>) t -> {
                    //对于所有的顶点都进行这样的运算相同减去_2_1是新数据
                    // _2_2是原图的数据，映射到一个Key上面
                    return t._2._1 - t._2._2;
                }).reduce((Function2<Integer, Integer, Integer>) (a, b) -> {
            //对于所有顶点相减的结果 进行求和
            return a + b;
        });
        if (check > 0)
            return propagateEdgeCount(g2);
        else
            return g;
    }

    static abstract class SerializableFunction1<T1, R> extends AbstractFunction1<T1, R> implements Serializable {}

    static abstract class SerializableFunction2<T1, T2, R> extends AbstractFunction2<T1, T2, R> implements Serializable {}
}

