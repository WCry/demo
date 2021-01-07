package com.zxp.demo

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession
import org.apache.spark.graphx.{Edge, Graph, VertexId}
/**
 * @author zhangxuepei
 * @since 3.0
 * VD : 顶点的属性的数据类型。
 * ED : 边的属性的数据类型
 * VertexId : 顶点ID的类型
 * A ： Pregel message的类型。
 * graph：计算的输入的图
 * initialMsg : 图的每个顶点在首轮迭代时收到的初始化消息
 * maxIterations：最大迭代的次数
 * vprog：
 * vprog是用户定义的顶点程序，会运行在每一个顶点上，该vprog函数的功能是负责接收入站的message，
 * 并计算出的顶点的新属性值。
 * 在首轮迭代时，在所有的顶点上都会调用程序vprog函数，传人默认的defaultMessage；在次轮迭代时，只有接收到message消息的顶点才会调用vprog函数。
 */
object GraphxPregelTest {
  val spark = SparkSession
    .builder
    .appName(s"${this.getClass.getSimpleName}").master("local[2]")
    .getOrCreate()
  val sc = spark.sparkContext

  /**
   * 计算最短路径
   **/
  def shortestPath(): Unit = {
    //生成一个图对象
    val graph: Graph[Long, Double] = genGraph
    //打印出图的值 打印边的信息  起点ID 终点ID  起点属性  终点属性
    //开始时候 起点和终点的属性 都是0
    graph.triplets.foreach(t => {
      println(s"t.srcId=${t.srcId} t.dstId=${t.dstId}  t.srcAttr=${t.srcAttr} t.dstAttr=${t.dstAttr}")
    })

    val sourceId: VertexId = 1 // 计算顶点1到图各个顶点的最短路径
    // Initialize the graph such that all vertices except the root have distance infinity.
    val initialGraph = graph.mapVertices((id, att) =>
      if (id == sourceId) 0.0 else Double.PositiveInfinity)

    println("------------------------------")
    //打印出图的值
    initialGraph.triplets.foreach(t => {
      println(s"t.srcId=${t.srcId} t.dstId=${t.dstId}  t.srcAttr=${t.srcAttr} t.dstAttr=${t.dstAttr}")
    })

    val sssp:Graph[Double,Double] = initialGraph.pregel(Double.PositiveInfinity)(
      (vid, vidAttr, message) => math.min(vidAttr, message), // Vertex Program
      triplet => {
        // Send Message  发送消息 包括 起点的属性信息 和边上信息的和
        //和目标节点的属性进行比较
        if (triplet.srcAttr + triplet.attr < triplet.dstAttr) {
          Iterator((triplet.dstId, triplet.srcAttr + triplet.attr))
        } else {
          Iterator.empty
        }
      },
      (message_a, message_b) => math.min(message_a, message_b) // Merge Message
    )
    println("------------------------------")
    //打印出计算结果
    println(sssp.vertices.collect.mkString("\n"))
  }

  /**
   * 初始化图对象
   *
   * @return
   */
  private def genGraph(): Graph[Long, Double] = {
    val vertices: RDD[(VertexId, Long)] =
      sc.parallelize(Array(
        (1L, 0L),
        (2L, 0L),
        (3L, 0L),
        (4L, 0L),
        (5L, 0L),
        (6L, 0L))
      )
    // Create an RDD for edges
    val edges: RDD[Edge[Double]] =
      sc.parallelize(Array(
        Edge(1L, 2L, 1.0),
        Edge(1L, 4L, 1.0),
        Edge(1L, 5L, 1.0),
        Edge(2L, 3L, 1.0),
        Edge(4L, 3L, 1.0),
        Edge(5L, 4L, 1.0),
        Edge(3L, 6L, 1.0)
      )
      )
    val graph: Graph[Long, Double] = Graph(vertices, edges, 0)
    graph
  }

  def main(args: Array[String]) {
    shortestPath
  }
}
