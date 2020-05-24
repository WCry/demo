package collectiontest;

import java.util.HashMap;
import java.util.Map;

/**
 * user:zxp
 * Day:2020,03,04
 **/

public class MapStudentTest {
    public static void main(String[] args) {
        Map<MapStudentTest, String> map = new HashMap<MapStudentTest, String>();
        long l1 = System.currentTimeMillis();
        MapStudentTest ds=null;
        for(int i = 0;i<10000;i++){
            ds=new MapStudentTest("dy", i);
            map.put(ds, ""+i);

        }
        System.out.println(map.size());
        System.out.println(ds.hashCode());
        System.out.println(map.get(ds));
        long l2 = System.currentTimeMillis();
        System.out.println(map.get(new MapStudentTest("dy9999",9999)));
        long l3 = System.currentTimeMillis();
        System.out.println((l2-l1));
        System.out.println((l3-l2));
    }


    public String name;
    public Integer age;

    MapStudentTest(String name, Integer age){
        this.name = name;
        this.age = age;
    }
}
