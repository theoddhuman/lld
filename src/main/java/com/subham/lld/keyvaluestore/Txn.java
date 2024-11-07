package com.subham.lld.keyvaluestore;

import java.util.HashMap;
import java.util.Map;

public class Txn<K, V> {
    private final Map<K, V> changes;
    private KVStore<K,V> kvStore;
    private boolean committed;

    public Txn(KVStore<K,V> kvStore) {
        changes = new HashMap<>();
        this.kvStore = kvStore;
    }

    public V get(K key) {
        return this.changes.get(key);
    }

    public void put(K key, V value) {
        this.changes.put(key, value);
    }

    public void delete(K key) {
        this.changes.put(key, null);
    }

    public boolean hasChanged(K key) {
        return changes.containsKey(key);
    }

    public void commit() {
        if(committed) {
            throw new IllegalStateException("Already committed");
        }
        for(Map.Entry<K,V> entry : changes.entrySet()) {
            if(entry.getValue() == null) {
                kvStore.delete(entry.getKey());
            } else {
                kvStore.put(entry.getKey(), entry.getValue());
            }
        }
        committed = true;
        changes.clear();
    }

    public void rollback() {
        if(committed) {
            throw new IllegalStateException("Already committed");
        }
        changes.clear();
    }

}
