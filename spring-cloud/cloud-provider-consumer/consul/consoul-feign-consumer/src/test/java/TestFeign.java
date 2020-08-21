import com.example.consulfeignapi.HelloService;
import com.example.consulfeignconsumer.ConsulFeignConsumerApplication;
import com.example.consulfeignconsumer.HelloTestController;
import feign.codec.Decoder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

/**
 * @author zhangxuepei
 * @since 3.0
 */
@SpringBootTest(classes = ConsulFeignConsumerApplication.class)
public class TestFeign {
    @Autowired
    HelloService helloService;
    @Autowired
    HelloTestController helloTestController;

    @Test
    public void Test()  {
        System.out.println(helloService.hello("dasd"));
//        ResponseEntity<byte[]> responseEntity = helloService.hello2();
//        System.out.println(responseEntity.getHeaders().get("aaaaa"));

    }
}