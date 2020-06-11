package cache;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.EvictionAdvisor;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;

/**
 * @author zhangxuepei
 * @since 3.0
 * https://blog.csdn.net/sinat_30071601/article/details/70195685
 * Encache 使用
 * https://www.jianshu.com/p/154c82073b07
 */
public class TestEhCache {
    public static void main(String[] args) throws InterruptedException {
        HashMap<String, LocalDateTime> cacheTimeManage=new HashMap<>();
        long cacheSecond = 2000;
        String cacheAlias = "CACHEMANAGE";
        CacheConfiguration<String, Object> cacheConfiguration = CacheConfigurationBuilder.
                newCacheConfigurationBuilder(String.class, Object.class, ResourcePoolsBuilder.heap(100))
                .withExpiry(ExpiryPolicyBuilder.timeToIdleExpiration(Duration.of(cacheSecond, ChronoUnit.MILLIS)))
                //设置驱逐顾问 及 缓存需要移出时候的处理
                .withEvictionAdvisor((s, object) -> {
                    long dd= cacheTimeManage.get(s).until(LocalDateTime.now(),ChronoUnit.MILLIS);
                   System.out.println(dd);
                    System.out.println("失效了需要处理了："+s);
                    return true;
                }).build();
        CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder().withCache(cacheAlias,
                cacheConfiguration).build();
        cacheManager.init();
        Cache cache =cacheManager.getCache(cacheAlias,String.class, Object.class);
        for (int i = 0; i < 100; i++) {
            cacheTimeManage.put("12"+i,LocalDateTime.now());
            cache.put("12"+i,"ddsd");
            Thread.sleep(1000);
        }
        Thread.sleep(1000);
    }
}
