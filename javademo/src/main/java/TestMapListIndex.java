import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TestMapListIndex {
    public static void main(String[] args) {
        final Map<Object, Integer> values = new LinkedHashMap<>();
        values.put(2, 3);
        System.out.println(values.get(2));
        final List<Object> valuess = new ArrayList<>();
        Integer integedasdr = 12545;
        valuess.add(integedasdr);
        Integer nidasdas = 12545;
        System.out.println(integedasdr.equals(nidasdas));
        System.out.println(valuess.indexOf(nidasdas));
        List<String> test = new ArrayList<>();
        test.add("a");
        test.add("b");
        test.add("b");
        test.add("b");
        test.add("c");
        List<String> removeString = new ArrayList<>();
        for (String abc : test) {
            if (abc == "b") {
                removeString.add(abc);
            }
        }
        test.removeAll(removeString);
        test.forEach(System.out::println);
    }
}
