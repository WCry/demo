import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhangxuepei
 * @since 3.0
 */
public class StreamTest {
    public static void main(String[] args) {
        List<String> dsa=new ArrayList<>();
        dsa.add("dsadsad");
        dsa.add("dsad");
        List dsad= dsa.parallelStream().filter(s -> false).collect(Collectors.toList());
        System.out.println(dsad);
    }
}


