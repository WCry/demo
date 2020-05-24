import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author zhangxuepei
 * @since 3.0
 */
public class StreamFlatMap {
    interface A{

        public String getA();
    }
    static class B implements A{
        String b;
        String a;
        public B(String b,String a){
            this.b=b;
            this.a=a;
        }
        public String getB() {
            return b;
        }

        public void setB(String b) {
            this.b = b;
        }

        @Override
        public String getA() {
            return null;
        }
    }
    static class  C {
        public List<? extends A> sdad;

        public List<? extends A> getSdad() {
            return sdad;
        }

        public void setSdad(List<? extends A> sdad) {
            this.sdad = sdad;
        }
    }

    public static void main(String[] args) {
        List<B> bList=new ArrayList<>();
        bList.add(new B("1","2"));
        bList.add(new B("3","4"));

        bList.add(new B("5","6"));
        bList.add(new B("7","8"));
        bList.add(new B("9","10"));
        C c =new C();
        c.setSdad(bList);
        Map<String,C> dasd=new HashMap<>();
        dasd.put("",c);
        List<A>  dsad= dasd.values().stream().flatMap(ds->ds.getSdad().stream())
                .collect(Collectors.toList());
    }
}
