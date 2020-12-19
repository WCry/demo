package scalademo.util

/**
 * @author zhangxuepei
 * @since 3.0
 */
object AppStartInfo{
  val AppStartPath = sys.props.get("user.dir").get
}
