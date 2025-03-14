package com.subham.lld.keyvaluestoretxn;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

public class TxnManager<K,V> {
    private final KVStore<K, VersionedValue<V>> kvStore;

    //This allows each thread to have its own transaction context
    private final ThreadLocal<Txn<K,V>> currentTxn;

    private AtomicLong globalVersion;

    public TxnManager(KVStore<K,VersionedValue<V>> kvStore) {
        this.kvStore = kvStore;
        currentTxn = new ThreadLocal<>();
        globalVersion = new AtomicLong(0);
    }

    public void beginTransaction() {
        Txn<K, V> txn = new Txn<>();
        currentTxn.set(txn);
    }

    public void put(K key, V value) {
        Txn<K,V> txn = currentTxn.get();
        if(Objects.nonNull(txn)) {
            txn.put(key, value);
        } else {
            kvStore.put(key, new VersionedValue<>(value, globalVersion.incrementAndGet()));
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
        VersionedValue<V> versionedValue = kvStore.get(key);
        if(Objects.nonNull(txn)) {
            txn.updateReadVersion(key, versionedValue.getVersion());
        }
        return kvStore.get(key).getValue();
    }

    public void commit() {
        Txn<K, V> txn = currentTxn.get();
        if(txn.isCommitted()) {
            throw new IllegalStateException("Already committed");
        }
        for(Map.Entry<K, Long> entry : txn.getReadMap().entrySet()) {
            long currentVersion = kvStore.get(entry.getKey()).getVersion();
            if(currentVersion != entry.getValue()) { //conflict
                rollback();
                return;
            }
        }
        for(Map.Entry<K,V> entry : txn.getChanges().entrySet()) {
            if(entry.getValue() == null) {
                kvStore.delete(entry.getKey());
            } else {
                kvStore.put(entry.getKey(), new VersionedValue<>(entry.getValue(), globalVersion.incrementAndGet()));
            }
        }
        txn.clearChanges();
    }

    public void rollback() {
        Txn<K, V> txn = currentTxn.get();
        if(txn.isCommitted()) {
            throw new IllegalStateException("Already committed");
        }
        txn.clearChanges();
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
