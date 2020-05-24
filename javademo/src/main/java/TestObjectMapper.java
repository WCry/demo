import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * JAVA 8 API 中  时间计算
 * https://www.cnblogs.com/wbxk/p/9598518.html
 */
public class TestObjectMapper {
    public static void main(String[] args) throws InterruptedException {
        B b=new TestObjectMapper.B();
        b.setB("b");
        b.setA("a");
        A a=b;
        ObjectMapper objectMapper=new ObjectMapper();
        try {
            System.out.println(objectMapper.writeValueAsString(a));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
    static class B extends A{
        String b;
        public String getB() {
            return b;
        }
        public void setB(String b) {
            this.b = b;
        }
    }
    static class A{
        String a;

        public String getA() {
            return a;
        }

        public void setA(String a) {
            this.a = a;
        }
    }
}
