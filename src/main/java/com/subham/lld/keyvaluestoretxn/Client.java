package com.subham.lld.keyvaluestoretxn;

/**
 * Functional Requirements:
 * 1. Basic Key-Value Operations: Support retrieving, storing, updating, and deleting key-value pairs. (GET, SET, DELETE)
 * 2. Transaction Management: Ability to execute multiple operations atomically - commit and rollback
 * 3. Concurrency Control: Handle concurrent access to keys, ensuring data integrity using optimistic locking.
 */
public class Client {
    public static void main(String[] args) {

        TxnManager<String, Integer> txnManager = new TxnManager<>(new KVStore<>());
        txnManager.beginTransaction();
        txnManager.put("k1", 11);
        txnManager.put("k2", 22);
        txnManager.delete("k2");
        txnManager.put("k2", 12);
        txnManager.commit();
        txnManager.closeTransaction();

        System.out.println(txnManager.get("k1"));
        System.out.println(txnManager.get("k2"));
    }
}
