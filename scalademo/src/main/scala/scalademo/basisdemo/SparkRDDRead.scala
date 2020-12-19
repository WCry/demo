package scalademo.basisdemo

/**
 * @author zhangxuepei
 * @since 3.0
 */
case object SparkRDDRead {
  def main(args: Array[String]): Unit = {

    AppMain.main(Array("Runoob", "Baidu", "Google"));
    val point = new Point(1, 2);
    // 移到一个新的位置
    point.move(10, 10);
  }
}
