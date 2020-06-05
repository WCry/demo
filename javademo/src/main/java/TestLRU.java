import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author zhangxuepei
 * @since 3.0
 */
public class TestLRU {
    public static void main(String[] args) {
        LRU<Character, Integer> lru = new LRU<Character, Integer>(2, 0.75f, true);

        String s = "abcdefgijkdadasdassdaslh";
        for (int i = 0; i < s.length(); i++) {
            lru.put(s.charAt(i), i);
            System.out.println("还在写入");
        }
        System.out.println("LRU中key为h的Entry的值为： " + lru.get('h'));
        System.out.println("LRU的大小 ：" + lru.size());
        System.out.println("LRU ：" + lru);
    }

    static class LRU<K, V> extends LinkedHashMap<K, V> implements Map<K, V> {

        private static final long serialVersionUID = 1L;
        private int initialCa;

        public LRU(int initialCapacity, float loadFactor, boolean accessOrder) {
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
                System.out.println(eldest.getValue());
                System.out.println(size() + "移出数据");
                return true;
            }
            return false;
        }
    }
}
