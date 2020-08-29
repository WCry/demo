package com.zxp.utils;

import com.google.common.math.LongMath;
import com.google.common.primitives.Longs;
import org.springframework.data.redis.core.RedisTemplate;

import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;
import java.util.stream.LongStream;


public class RedisBitmaps {
    private static final String BASE_KEY = "bloomfilter";
    private static final String CURSOR = "cursor";
    private long bitSize;
    RedisExecutor<Boolean> redisExecutor;
    RedisBitmaps(RedisTemplate redisTemplate,long bits) {
        this.bitSize = LongMath.divide(bits, 64, RoundingMode.CEILING) * Long.SIZE;//位数组的长度，相当于n个long的长度
        redisExecutor=new RedisExecutor(redisTemplate);
        if (bitCount() == 0) {
            redisExecutor.execute(conn -> conn.setBit(currentKey().getBytes(), bitSize - 1, false));
        }
    }

    boolean get(long[] offsets) {
        for (long i = 0; i < cursor() + 1; i++) {
            final long cursor = i;
            //只要有一个cursor对应的bitmap中，offsets全部命中，则表示可能存在
            boolean match = Arrays.stream(offsets).boxed()
                    .map(offset -> {
                        List<Boolean> list = redisExecutor
                                .executePipelined(conn -> conn.getBit(genkey(cursor).getBytes(), offset));
                        return !list.contains(false);
                    })
                    .allMatch(b -> b == null ? false : b);
            if (match)
                return true;
        }
        return false;
    }

    boolean get(final long offset) {
        Boolean rst = redisExecutor.execute(conn -> conn.getBit(currentKey().getBytes(), offset));
        return rst == null ? false : rst;
    }

    boolean set(long[] offsets) {
        if (cursor() > 0 && get(offsets)) {
            return false;
        }
        boolean bitsChanged = false;
        for (long offset : offsets)
            bitsChanged |= set(offset);
        return bitsChanged;
    }

    boolean set(long offset) {
        if (!get(offset)) {
            redisExecutor.execute(conn -> conn.setBit(currentKey().getBytes(), offset, true));
            return true;
        }
        return false;
    }
    long bitCount() {
        Long rst = redisExecutor.execute(conn -> conn.bitCount(currentKey().getBytes()));
        return rst == null ? 0 : rst;
    }

    long bitSize() {
        return this.bitSize;
    }

    private String currentKey() {
        return genkey(cursor());
    }

    private String genkey(long cursor) {
        return BASE_KEY + "-" + cursor;
    }

    private long cursor() {
        String cursor = redisExecutor.execute(conn -> conn.get(CURSOR.getBytes()));
        return cursor == null ? 0 : Longs.tryParse(cursor);
    }

    void ensureCapacityInternal() {
        if (bitCount() * 2 > bitSize())
            grow();
    }

    void grow() {
        Long cursor = redisExecutor.execute((conn) -> conn.incr(CURSOR.getBytes()));
        redisExecutor.execute(conn -> conn.setBit(genkey(cursor).getBytes(), bitSize - 1, false));
    }

    void reset() {
        byte[][] keys = LongStream.range(0, cursor() + 1).boxed().map(k -> genkey(k).getBytes()).toArray(byte[][]::new);
        redisExecutor.execute(conn -> conn.del(keys));
        redisExecutor.execute(conn -> conn.set(CURSOR.getBytes(), "0".getBytes()));
        redisExecutor.execute(conn -> conn.setBit(currentKey().getBytes(), bitSize - 1, false));
    }
}
