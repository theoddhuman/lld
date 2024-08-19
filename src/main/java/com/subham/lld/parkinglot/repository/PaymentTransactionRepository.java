package com.subham.lld.parkinglot.repository;

import com.subham.lld.parkinglot.model.payment.PaymentTransaction;
import com.subham.lld.parkinglot.model.payment.types.PaymentStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author subham.paul
 */
public class PaymentTransactionRepository {
    private static long id = 0;
    private static final Map<Long, Object> paymentTransactions = new HashMap<>();

    public synchronized <T> PaymentTransaction<T> save(PaymentTransaction<T> paymentTransaction) {
        paymentTransaction.setId(++id);
        paymentTransactions.put(paymentTransaction.getId(), paymentTransaction);
        return paymentTransaction;
    }

    public void updateStatus(long id, PaymentStatus paymentStatus) {
        ((PaymentTransaction<?>)paymentTransactions.get(id)).setStatus(paymentStatus);
    }

    public List<PaymentTransaction<?>> get() {
        return paymentTransactions.values().stream()
                .map(paymentTransaction -> (PaymentTransaction<?>)paymentTransaction)
                .collect(Collectors.toList());
    }
}
