import java.util.ArrayList;
import java.util.Collections;

/**
 * @author zhangxuepei
 * @since 3.0
 */
public class TestLRUTest {
    public static void main(String[] args) {
        Integer[] sort1=new Integer[5];
        sort1[0]=9;
        sort1[1]=5;
        sort1[2]=2;
        sort1[3]=4;
        sort1[4]=8;
        ArrayList arrayList=new ArrayList();
        arrayList.add(1);
        arrayList.add(9);
        arrayList.add(3);
        Collections.sort(arrayList);
        for (Object o : arrayList) {
            System.out.println(0);
        }
    }
}