package geomertry;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

/**
 * @author zhangxuepei
 * @since 3.0
 */
public class SimpleJsonDeserializer extends JsonDeserializer<SimpleFeaS> {
    @Override
    public SimpleFeaS deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        return null;
    }
}
