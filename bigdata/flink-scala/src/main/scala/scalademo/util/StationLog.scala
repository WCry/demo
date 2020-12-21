package scalademo.util

/**
 * 基站日志
 * @param sid      基站的id
 * @param callOut  主叫号码
 * @param callIn  被叫号码
 * @param callType 呼叫类型
 * @param callTime 呼叫时间 (毫秒)
 * @param duration 通话时长 （秒）
 */
case class StationLog(sid: String, callOut: String, callIn: String, callType: String, callTime: Long, duration: Long)
