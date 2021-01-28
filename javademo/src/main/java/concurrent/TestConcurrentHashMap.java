package concurrent;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhangxuepei
 * @since 3.0
 */
public class TestConcurrentHashMap {
    public static void main(String[] args) {
        ConcurrentHashMap<Integer,Integer> concurrentHashMap=new ConcurrentHashMap<>();
        for(int i=1;i<=10;i++){
            concurrentHashMap.put(i,i);
        }
       Double dsa= concurrentHashMap.reduceToDouble(2, (o, o2) -> o2,10, (left, right) -> left+right);
        System.out.println(dsa);
    }
}
