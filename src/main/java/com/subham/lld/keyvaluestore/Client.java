package com.subham.lld.keyvaluestore;

/**
 * This is a key value store with transactional support like commit and rollback
 */
public class Client {
    public static void main(String[] args) {
        KVStore<String, Integer> kvStore = new KVStore<>();

        kvStore.put("k1", 1);
        kvStore.put("k2", 2);
        System.out.println(kvStore.get("k1"));
        System.out.println(kvStore.get("k2"));

        TxnManager<String, Integer> txnManager = new TxnManager<>(kvStore);
        txnManager.beginTransaction();
        txnManager.put("k1", 11);
        txnManager.delete("k2");
        txnManager.put("k2", 12);
        txnManager.commit();
        txnManager.closeTransaction();

        System.out.println(kvStore.get("k1"));
        System.out.println(kvStore.get("k2"));
    }
}
