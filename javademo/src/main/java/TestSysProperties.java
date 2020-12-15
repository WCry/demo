import java.util.Map;
import java.util.Properties;


/**
 * @author zhangxuepei
 * @since 3.0
 */
public class TestSysProperties {
    public static void main(String[] args) {
        //获取系统 启动设置的属性值
        Properties properties=System.getProperties();
        for (Map.Entry<Object, Object> objectObjectEntry : properties.entrySet()) {
            System.out.println(objectObjectEntry.getKey()+"  value:"+objectObjectEntry.getValue());
        }
    }
}
