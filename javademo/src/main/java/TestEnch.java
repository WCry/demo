import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;

import java.time.temporal.ChronoUnit;

/**
 * @author zhangxuepei
 * @since 3.0
 */
public class TestEnch {
    public static void main(String[] args) {
        long cacheSecond=1;
        String cacheAlias="CACHEMANAGE";
        CacheConfiguration<String, Object> cacheConfiguration = CacheConfigurationBuilder
                .newCacheConfigurationBuilder(String.class, Object.class, ResourcePoolsBuilder.heap(100))
                .withExpiry(ExpiryPolicyBuilder.timeToIdleExpiration(
                        java.time.Duration.of(cacheSecond, ChronoUnit.SECONDS)))
                .build();
        CacheManager cacheManager=CacheManagerBuilder.newCacheManagerBuilder().withCache(cacheAlias,cacheConfiguration).build();
        Cache cache= cacheManager.createCache(cacheAlias,cacheConfiguration);
        cache.clear();
    }
}
