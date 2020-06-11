package cache;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author zhangxuepei
 * @since 3.0
 */
public class TestLRULinkedHashMapCache {
    public static void main(String[] args) {
        LinkedHasMapCache<Character, Integer> lruCache = new LinkedHasMapCache<Character, Integer>(2, 0.75f, true);

        String s = "abcdefgijkdadasdassdaslh";
        for (int i = 0; i < s.length(); i++) {
            lruCache.put(s.charAt(i), i);
            System.out.println("还在写入");
        }
        System.out.println("LRU中key为h的Entry的值为：" + lruCache.get('h'));
        System.out.println("LRU的大小:" + lruCache.size());
        System.out.println("LRU:" + lruCache);
    }

    static class LinkedHasMapCache<K, V> extends LinkedHashMap<K, V> implements Map<K, V> {

        private static final long serialVersionUID = 1L;
        private int initialCa;

        public LinkedHasMapCache(int initialCapacity, float loadFactor, boolean accessOrder) {
            super(initialCapacity, loadFactor, accessOrder);
            initialCa = initialCapacity;
        }

        /**
         * @param eldest
         *
         * @return
         *
         * @see java.util.LinkedHashMap#removeEldestEntry(java.util.Map.Entry)
         */
        @Override
        protected boolean removeEldestEntry(java.util.Map.Entry<K, V> eldest) {
            // TODO Auto-generated method stub
            if (size() > initialCa) {
                //是同步的 put 时候 调用  然后移出元素
                System.out.println(eldest.getValue());
                System.out.println(size() + "移出数据");
                return true;
            }
            return false;
        }
    }
}
