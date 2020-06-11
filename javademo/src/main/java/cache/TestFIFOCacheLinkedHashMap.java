package cache;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author zhangxuepei
 * @since 3.0
 */
public class TestFIFOCacheLinkedHashMap {
    public static void main(String[] args) {
        LinkedHasMapCache<Character, Integer> lruCache = new LinkedHasMapCache<>(2, 0.75f, false);
        String s = "abcdfsertgh";
        for (int i = 0; i < s.length(); i++) {
            lruCache.put(s.charAt(i), i);
            System.out.println("写入数据");
        }
        System.out.println("LRU中key为h的Entry的值为： " + lruCache.get('h'));
        System.out.println("FIFO的大小 ：" + lruCache.size());
        System.out.println("FIFO ：" + lruCache);
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
         * @see LinkedHashMap#removeEldestEntry
         */

        @Override
        protected boolean removeEldestEntry(Entry<K, V> eldest) {
            // TODO Auto-generated method stub
            if (size() > initialCa) {
                System.out.println("移出数据:"+eldest.getKey());
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return true;
            }
            return false;
        }
    }
}
