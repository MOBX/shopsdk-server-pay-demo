package com.mob.etrade.server.demo.util;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 緩存示例,請不要用於生產
 *
 * @author xianyi
 */
public class CacheUtils {
    private static final ItemCache<String, Item<Map<String, String>>> CACHE = new ItemCache<>(16);

    public static void put(Long expired, String key, Map<String, String> value) {
        CacheUtils.CACHE.put(key, new Item<>(expired, value));
    }

    public static Map<String, String> get(String key) {
        Item<Map<String, String>> item = CACHE.get(key);
        if (item != null) {
            return item.getData();
        }
        return Collections.emptyMap();
    }

    private static class ItemCache<K, V extends Item> extends LinkedHashMap<K, V> {

        private static final long serialVersionUID = -3268204889118710308L;

        private Lock lock = new ReentrantLock();

        public ItemCache(int initialCapacity) {
            super(initialCapacity + 1, 1.0F, true);
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
            return eldest.getValue().isExpired();
        }

        @Override
        public V put(K key, V value) {
            try {
                lock.lock();
                return super.put(key, value);
            } finally {
                lock.unlock();
            }
        }

        @Override
        public V get(Object key) {
            try {
                lock.lock();
                return super.get(key);
            } finally {
                lock.unlock();
            }
        }
    }

    private static class Item<T> {
        private Long insertTime;

        private Long cacheTime;

        private T data;

        public Item() {
            this.insertTime = System.currentTimeMillis();
        }

        public Item(Long cacheTime, T data) {
            this();
            this.cacheTime = cacheTime;
            this.data = data;
        }

        public boolean isExpired() {
            return System.currentTimeMillis() - insertTime > cacheTime;
        }

        public Long getInsertTime() {
            return insertTime;
        }

        public void setInsertTime(Long insertTime) {
            this.insertTime = insertTime;
        }

        public Long getCacheTime() {
            return cacheTime;
        }

        public void setCacheTime(Long cacheTime) {
            this.cacheTime = cacheTime;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }
    }
}
