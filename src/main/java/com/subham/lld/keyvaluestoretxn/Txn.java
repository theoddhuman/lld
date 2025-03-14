package com.subham.lld.keyvaluestoretxn;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Txn<K, V> {
    //to store changes made under a transaction
    private final Map<K, V> changes;
    //to track versions for optimistic locking
    private final Map<K, Long> readMap;
    private boolean committed;

    public Txn() {
        changes = new HashMap<>();
        readMap = new HashMap<>();
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

    public void updateReadVersion(K key, Long version) {
        this.readMap.put(key, version);
    }

    public void clearChanges() {
        this.changes.clear();
    }
}
