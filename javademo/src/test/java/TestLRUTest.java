import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author zhangxuepei
 * @since 3.0
 */
public class TestLRUTest {
    public static void main(String[] args) {
        List<String> blockArrays = new ArrayList<>();
        blockArrays.add("22");
        blockArrays.add("33");
        blockArrays.add("44");
        blockArrays.sort((o1, o2) -> {
            if (o1.equals("33")) {
                return -1;
            }
            return 0;
        });
    }
}