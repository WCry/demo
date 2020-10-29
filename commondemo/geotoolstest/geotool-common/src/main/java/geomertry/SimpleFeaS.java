package geomertry;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.opengis.feature.simple.SimpleFeature;

/**
 * @author zhangxuepei
 * @since 3.0
 * JackJson 实现自定义序列化对象
 * https://blog.csdn.net/zhao1949/article/details/79281967
 */
@JsonSerialize(using = SimpleJsonSerializer.class)
@JsonDeserialize(using = SimpleJsonDeserializer.class)
public class SimpleFeaS {
    private SimpleFeature source;

    public SimpleFeature getSource() {
        return source;
    }

    public void setSource(SimpleFeature source) {
        this.source = source;
    }
}
