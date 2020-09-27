import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;

/**
 * @author zhangxuepei
 * @since 3.0
 */
public class TestHashSet {

    public static void main(String[] args) {
        Set set = new HashSet();
        set.add(null);
        //重复添加不会报错
        set.add(null);
        //添加一个对象
        set.add(1);
        set.add("a");
        System.out.println(set);
        //删除一个对象
        set.remove(1);
        System.out.println(set);
        //判断是否包含某个对象
        System.out.println(set.contains("a"));
        //清空集合
        set.clear();
        set.add("a");
        set.add("b");
        set.add("c");
        set.add("d");
        //可以存放null
        set.add(null);
        //使用迭代器遍历集合
        Iterator it = set.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
        //for each迭代集合
        //将set中的每一个值取出来赋值给obj
        for (Object obj : set) {
            System.out.println(obj);
        }
        //取得set的长度
        System.out.println(set.size());
        //存放指定数据类型(string)的对象
        Set<String> stringSet = new HashSet<String>();
        stringSet.add("a");
        stringSet.add("b");
        for (String str : stringSet) {
            System.out.println(str);
        }
    }
}
