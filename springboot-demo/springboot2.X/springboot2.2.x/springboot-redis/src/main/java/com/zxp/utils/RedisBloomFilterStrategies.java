package com.zxp.utils;

import com.google.common.hash.Funnel;
import com.google.common.hash.Hashing;
import com.google.common.primitives.Longs;

/**
 * @Auther: syh
 * @Date: 2020/7/10
 * @Description:
 */
public enum RedisBloomFilterStrategies implements RedisBloomFilter.Strategy {

    MURMUR128_MITZ_64() {
        @Override
        public <T> boolean put(T object, Funnel<? super T> funnel, int numHashFunctions, RedisBitmaps bits) {
            long bitSize = bits.bitSize();
            byte[] bytes = Hashing.murmur3_128().hashObject(object, funnel).asBytes();
            long hash1 = lowerEight(bytes);
            long hash2 = upperEight(bytes);

            boolean bitsChanged = false;
            long combinedHash = hash1;

            long[] offsets = new long[numHashFunctions];
            for (int i = 0; i < numHashFunctions; i++) {
                offsets[i] = (combinedHash & Long.MAX_VALUE) % bitSize;
                combinedHash += hash2;
            }
            bitsChanged = bits.set(offsets);
            bits.ensureCapacityInternal();//自动扩容
            return bitsChanged;
        }

        @Override
        public <T> boolean mightContain(T object, Funnel<? super T> funnel, int numHashFunctions, RedisBitmaps bits) {
            long bitSize = bits.bitSize();
            byte[] bytes = Hashing.murmur3_128().hashObject(object, funnel).asBytes();
            long hash1 = lowerEight(bytes);
            long hash2 = upperEight(bytes);
            long combinedHash = hash1;

            long[] offsets = new long[numHashFunctions];
            for (int i = 0; i < numHashFunctions; i++) {
                offsets[i] = (combinedHash & Long.MAX_VALUE) % bitSize;
                combinedHash += hash2;
            }
            return bits.get(offsets);
        }

        private /* static */ long lowerEight(byte[] bytes) {
            return Longs.fromBytes(
                    bytes[7], bytes[6], bytes[5], bytes[4], bytes[3], bytes[2], bytes[1], bytes[0]);
        }

        private /* static */ long upperEight(byte[] bytes) {
            return Longs.fromBytes(
                    bytes[15], bytes[14], bytes[13], bytes[12], bytes[11], bytes[10], bytes[9], bytes[8]);
        }
    }
}
