package scalademo.basisdemo

/**
 * @author zhangxuepei
 * @since 3.0
 * Point class 定义是一个对象的DDL
 * new  得到一个实际对象
 * object 定义一个实际的对象
 */
class Point(xc: Int, yc: Int) {
  var x: Int = xc
  var y: Int = yc

  def move(dx: Int, dy: Int) {
    x = x + dx
    y = y + dy
    println ("x 的坐标点: " + x);
    println ("y 的坐标点: " + y);
  }
}
