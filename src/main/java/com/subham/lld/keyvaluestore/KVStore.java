package com.subham.lld.keyvaluestore;

import java.util.HashMap;
import java.util.Map;

public class KVStore<K, V> {
    private final Map<K, V> keyValueMap;

    public KVStore() {
        keyValueMap = new HashMap<>();
    }

    public V get(K key) {
        return this.keyValueMap.get(key);
    }

    public void put(K key, V value) {
        this.keyValueMap.put(key,value);
    }

    public void delete(K key) {
        this.keyValueMap.remove(key);
    }
}
