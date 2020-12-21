package scalademo.util

import java.net.{URL, URLDecoder}

import org.apache.flink.api.common.functions.RichFlatMapFunction
import org.apache.flink.api.common.state.{ValueState, ValueStateDescriptor}
import org.apache.flink.api.java.functions.KeySelector
import org.apache.flink.configuration.Configuration
import org.apache.flink.streaming.api.datastream.{DataStreamSink, SingleOutputStreamOperator}
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment
import org.apache.flink.util.Collector
import org.apache.flink.streaming.api.scala._

/**
 * 第一种方法的实现
 * 统计每个手机的呼叫时间间隔，单位是毫秒
 */
object TestKeyedState1 {

  def main(args: Array[String]): Unit = {
    val streamEnv = StreamExecutionEnvironment.getExecutionEnvironment
    import org.apache.flink.streaming.api.scala._
    println(getClass.getResource("/station.log").getPath)
    //读取文件数据
    val data : SingleOutputStreamOperator[StationLog] = streamEnv.readTextFile(getClass.getResource("/station.log").getPath)
      .map(line => {
        val arr = line.split(",")
       StationLog(arr(0).trim, arr(1).trim, arr(2).trim, arr(3).trim, arr(4).trim.toLong, arr(5).trim.toLong)
      })
    data.keyBy((in: StationLog) => in.callOut) // 按照主键分组
      .flatMap(new CallIntervalFunc())  // 定义一个富函数
      .print()
    streamEnv.execute()
  }

  /**
   * 定义一个富函数：
   *
   * 输出时一个二元组（手机号码，时间间隔）
   */
  class CallIntervalFunc extends RichFlatMapFunction[StationLog, (String, Long)] {
    // 定义一个状态，用于保存前一次呼叫的的时间
    var preCallTimeState: ValueState[Long] = _

    override def open(parameters: Configuration): Unit = {
      // 状态描述
      val stateDescriptor = new ValueStateDescriptor[Long]("pre", classOf[Long])
      // 通过上下文，创建一个状态
      preCallTimeState = getRuntimeContext.getState(stateDescriptor)
    }

    override def flatMap(in: StationLog, collector: Collector[(String, Long)]): Unit = {
      var pre = preCallTimeState.value()
      if (pre == null || pre == 0) { // 第一次呼叫
        preCallTimeState.update(in.callTime)
      } else {
        val interval = Math.abs(in.callTime - pre)
        collector.collect((in.callOut + " " + in.callIn, interval))
        // preCallTimeState.update(in.callTime)
      }
    }
  }
}
