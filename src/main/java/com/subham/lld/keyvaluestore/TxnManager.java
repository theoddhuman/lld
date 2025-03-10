package com.subham.lld.keyvaluestore;

import java.util.Objects;

public class TxnManager<K,V> {
    private KVStore<K,V> kvStore = new KVStore<>();
    private final ThreadLocal<Txn<K,V>> currentTxn;

    public TxnManager(KVStore<K,V> kvStore) {
        this.kvStore = kvStore;
        currentTxn = new ThreadLocal<>();
    }

    public void beginTransaction() {
        Txn<K, V> txn = new Txn<>(kvStore);
        currentTxn.set(txn);
    }

    public void put(K key, V value) {
        Txn<K,V> txn = currentTxn.get();
        if(Objects.nonNull(txn)) {
            txn.put(key, value);
        } else {
            kvStore.put(key, value);
        }
    }

    public void delete(K key) {
        Txn<K,V> txn = currentTxn.get();
        if(Objects.nonNull(txn)) {
            txn.delete(key);
        } else {
            kvStore.delete(key);
        }
    }

    public V get(K key) {
        Txn<K,V> txn = currentTxn.get();
        if(Objects.nonNull(txn) && txn.hasChanged(key)) {
            return txn.get(key);
        }
        return kvStore.get(key);
    }

    public void commit() {
        currentTxn.get().commit();
    }

    public void rollback() {
        currentTxn.get().rollback();
    }

    public void closeTransaction() {
        currentTxn.remove();
    }

    public boolean isTransactionActive() {
        return currentTxn.get() != null;
    }

    public Txn<K,V> getActiveTransaction() {
        return currentTxn.get();
    }
}
