package collectiontest;

import java.util.ArrayList;
import java.util.List;

/**
 * user:zxp
 * Day:2020,03,05
 **/
public class ListTest {
    public static void main(String[] args) {
        List<String> generics=new ArrayList<>();
        List notGenerics=new ArrayList();
        notGenerics.add(new Object());
        generics=notGenerics;
        notGenerics=generics;
        //互转 可能类型不一致  建议不要转换
    }
}
