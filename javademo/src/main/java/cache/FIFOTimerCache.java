package cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhangxuepei
 * @since 3.0
 * 实现 FIFO和定时的缓存
 */
public class FIFOTimerCache<V> {
    protected Logger logger = LoggerFactory.getLogger(FIFOTimerCache.class);
    private Map<String, Timer> cacheScheduleTask = new HashMap<>();
    private Map<String, V> cacheObjectLinkedHashMap;
    private String usingCacheObject;
    private String timeOutToCloseObject;
    private Long cacheTime;
    private Lock lock;
    private HashMap<String, LocalDateTime> cacheTimeManager=new HashMap<>();
    /**
     * 缓存时间是毫秒为单位
     *
     * @param cacheCount
     * @param cacheTime
     */
    public FIFOTimerCache(int cacheCount, long cacheTime) {
        this.cacheObjectLinkedHashMap = new CacheObjectLinkedHashMap(cacheCount);
        this.cacheTime = cacheTime;
        lock = new ReentrantLock();
    }



    public void cleanCache() {
        //将缓存对象清空
        cacheObjectLinkedHashMap.forEach((key, value) -> {
            closeObject(key,value);
        });
        //将时间调度清空
        cacheScheduleTask.forEach((key, value) -> {
            value.cancel();
        });
    }

    private void closeObject(String key,V cacheObject) {
        if (cacheObject instanceof Closeable) {
            try {
                logger.info("关闭缓存对象！");
                long times= cacheTimeManager.get(key).until(LocalDateTime.now(), ChronoUnit.MILLIS);
                System.out.println(times);
                if(times>cacheTime){
                    logger.error("超时关闭！！！！！！！！！！！！！！！！！！！！！！！！！");
                    System.out.println(times);
                }
                ((Closeable) cacheObject).close();
            } catch (Exception ex) {
                logger.error("对象关闭出错", ex);
                logger.warn("移出缓存时候，关闭对象出错");
            }
        }
    }

    public V put(String key, V value) {
        lock.lock();
        CacheTask cacheTask = new CacheTask(key);
        Timer timer = new Timer();
        timer.schedule(cacheTask, cacheTime);
        cacheTimeManager.put(key,LocalDateTime.now());
        cacheScheduleTask.put(key, timer);
        changeUsingObject(key);
        cacheObjectLinkedHashMap.put(key, value);
        lock.unlock();
        return value;
    }

    public V get(String key) {
        changeUsingObject(key);
        return cacheObjectLinkedHashMap.get(key);
    }

    private void changeUsingObject(String key) {
        lock.lock();
        usingCacheObject = key;
        lock.unlock();
    }

    public void remove(String key) {
        removeObject(key);
    }

    private void removeObject(String key) {
        lock.lock();
        try {
            if (usingCacheObject != null && usingCacheObject.equals(key)) {
                logger.info("缓存对象正在使用！" + key);
                changeTimeOutObject(key);
            } else {
                V cache = cacheObjectLinkedHashMap.remove(key);
                closeObject(key,cache);
                Timer timer = cacheScheduleTask.remove(key);
                timer.cancel();
                changeTimeOutObject(null);
            }
        } finally {
            lock.unlock();
        }
    }

    private void changeTimeOutObject(String key) {
        lock.lock();
        timeOutToCloseObject = key;
        lock.unlock();
    }

    public void canRemove() {
        if (timeOutToCloseObject != null) {
            logger.info("缓存对象在时间超过，使用完毕关闭！");
            removeObject(timeOutToCloseObject);
        }
        changeUsingObject(null);
    }


    class CacheTask extends TimerTask {
        private String cacheKey;

        public CacheTask(String key) {
            this.cacheKey = key;
        }

        @Override
        public void run() {
            logger.info("时间到了,需要移出:" + cacheKey);
            removeObject(cacheKey);
        }
    }

    class CacheObjectLinkedHashMap extends LinkedHashMap<String, V> {
        private int initialCa;

        public CacheObjectLinkedHashMap(int initialCapacity) {
            super(initialCapacity, 0.75f, false);
            initialCa = initialCapacity;
        }

        /**
         * @param eldest
         *
         * @return
         *
         * @see LinkedHashMap#removeEldestEntry(Map.Entry)
         */
        @Override
        protected boolean removeEldestEntry(Map.Entry<String, V> eldest) {
            if (size() > initialCa) {
                logger.info("缓存数量到了,需要移出" + eldest.getKey());
                //这里主动移出对象不需要在LinkedHasMap移出对象
                removeObject(eldest.getKey());
            }
            return false;
        }
    }
}
