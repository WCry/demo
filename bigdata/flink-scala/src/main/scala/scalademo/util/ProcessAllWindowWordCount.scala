package scalademo.util


import org.apache.flink.api.common.functions.FlatMapFunction
import org.apache.flink.streaming.api.functions.source.SourceFunction
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.api.scala.function.ProcessAllWindowFunction
import org.apache.flink.streaming.api.windowing.time.Time
import org.apache.flink.streaming.api.windowing.windows.TimeWindow
import org.apache.flink.util.Collector
import org.apache.flink.api.scala. _

import scala.collection.mutable


/**
 * @author zhangxuepei
 * @since 3.0
 */
object ProcessAllWindowWordCount {
  def main(args: Array[String]): Unit = {
    //设置环境
    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.createLocalEnvironment()
    //设置数据源       计算逻辑
    env.addSource(new SourceFunction[String] {
      override def run(ctx: SourceFunction.SourceContext[String]): Unit = {
        while (true) {
          ctx.collect("hello hadoop hello storm hello spark hello hello hello")
        }
      }
      override def cancel(): Unit = {}
    })
      .flatMap(_.split(" "))
      .map((_, 1))
      .timeWindowAll(Time.seconds(10), Time.seconds(10))
      .process(new ProcessAllWindowFunction[(String, Int), mutable.Map[String, Int], TimeWindow] {
        override def process(context: Context, elements: Iterable[(String, Int)], out: Collector[mutable.Map[String, Int]]): Unit = {
          val wordCountMap = mutable.Map[String, Int]()
          elements.foreach(kv => {
            wordCountMap.put(kv._1, wordCountMap.get(kv._1).getOrElse(0) + kv._2)
          })
          out.collect(wordCountMap)
        }
      }).flatMap(new FlatMapFunction[mutable.Map[String, Int], (String, Int)] {
      override def flatMap(value: mutable.Map[String, Int], out: Collector[(String, Int)]): Unit = {
        value.foreach(out.collect(_))
      }
    }).print()
    //提交任务
    env.execute("word count")
  }
}
