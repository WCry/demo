package geomertry;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.geotools.geojson.feature.FeatureJSON;

import java.io.IOException;

/**
 * @author zhangxuepei
 * @since 3.0
 */
public class SimpleJsonSerializer extends JsonSerializer<SimpleFeaS> {
    private FeatureJSON featureJSON=new FeatureJSON();
    @Override
    public void serialize(SimpleFeaS value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
    }
}
