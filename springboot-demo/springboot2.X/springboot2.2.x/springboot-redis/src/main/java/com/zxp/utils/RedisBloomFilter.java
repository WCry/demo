package com.zxp.utils;

import com.google.common.base.Predicate;
import com.google.common.hash.Funnel;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @Description: 基于redis和guava的bloomFilter
 */
public class RedisBloomFilter<T> implements Predicate<T>, Serializable {

    private final RedisBitmaps bits;
    private final int numHashFunctions;
    private final Funnel<? super T> funnel;
    private final RedisBloomFilter.Strategy strategy;

    private RedisBloomFilter(RedisBitmaps bits, int numHashFunctions, Funnel<? super T> funnel, RedisBloomFilter.Strategy strategy) {
        checkArgument(numHashFunctions > 0, "numHashFunctions (%s) must be > 0", numHashFunctions);
        checkArgument(
                numHashFunctions <= 255, "numHashFunctions (%s) must be <= 255", numHashFunctions);
        this.bits = checkNotNull(bits);
        this.numHashFunctions = numHashFunctions;
        this.funnel = checkNotNull(funnel);
        this.strategy = checkNotNull(strategy);
    }

    public static <T> RedisBloomFilter create(RedisTemplate redisTemplate,Funnel<? super T> funnel, int expectedInsertions, double fpp) {
        return create(redisTemplate,funnel, (long) expectedInsertions, fpp);
    }

    public static <T> RedisBloomFilter<T> create(RedisTemplate redisTemplate, Funnel<? super T> funnel, long expectedInsertions, double fpp) {
        return create(redisTemplate,funnel, expectedInsertions, fpp, RedisBloomFilterStrategies.MURMUR128_MITZ_64);
    }

    static <T> RedisBloomFilter<T> create(RedisTemplate redisTemplate,
            Funnel<? super T> funnel, long expectedInsertions, double fpp, RedisBloomFilter.Strategy strategy) {
        checkNotNull(funnel);
        checkArgument(
                expectedInsertions >= 0, "Expected insertions (%s) must be >= 0", expectedInsertions);
        checkArgument(fpp > 0.0, "False positive probability (%s) must be > 0.0", fpp);
        checkArgument(fpp < 1.0, "False positive probability (%s) must be < 1.0", fpp);
        checkNotNull(strategy);

        if (expectedInsertions == 0) {
            expectedInsertions = 1;
        }

        long numBits = optimalNumOfBits(expectedInsertions, fpp);
        int numHashFunctions = optimalNumOfHashFunctions(expectedInsertions, numBits);
        try {
            return new RedisBloomFilter<T>(new RedisBitmaps(redisTemplate,numBits), numHashFunctions, funnel, strategy);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Could not create BloomFilter of " + numBits + " bits", e);
        }
    }

    @Override
    public boolean apply(@Nullable T input) {
        return mightContain(input);
    }

    public boolean put(T object) {
        return strategy.put(object, funnel, numHashFunctions, bits);
    }

    public boolean mightContain(T object) {
        return strategy.mightContain(object, funnel, numHashFunctions, bits);
    }

    static long optimalNumOfBits(long n, double p) {
        if (p == 0) {
            p = Double.MIN_VALUE;
        }
        return (long) (-n * Math.log(p) / (Math.log(2) * Math.log(2)));
    }

    static int optimalNumOfHashFunctions(long n, long m) {
        return Math.max(1, (int) Math.round((double) m / n * Math.log(2)));
    }

    interface Strategy extends Serializable {
        <T> boolean put(T object, Funnel<? super T> funnel, int numHashFunctions, RedisBitmaps bits);

        <T> boolean mightContain(T object, Funnel<? super T> funnel, int numHashFunctions, RedisBitmaps bits);

        int ordinal();
    }
}
