package scalademo.basisdemo

/**
 * Hello scala
 * var:一个可变的变量
 * val:一个只读的变量
 */
object AppMain {
  def main(args: Array[String]) = {
    for (x <- args) {
      println(x)
    }
    args.foreach(Predef.println)
    args.foreach( f => println(f) )
    println(args(2))
    println(FunctionParam(splicingString))
  }

  def splicingString(v: Int): String = {
    var hello: String = "Hello";
    val scala: String = "Scala";
    hello = s"$hello" + " " + scala + "!";
    hello
  }

  /**
   * 传入函数作为参数
   *
   * @param fn 函数
   * @return String类型
   */
  def FunctionParam(fn: (Int) => String) = {
    fn(2)
  }
}
