package com.subham.lld.librarymanagement.service;

import com.subham.lld.librarymanagement.model.book.Fine;
import com.subham.lld.librarymanagement.model.payment.PaymentTransaction;

import java.time.LocalDate;

/**
 * @author subham.paul
 */
public class PaymentService {

    public boolean collectFine(Fine fine) {
        PaymentTransaction paymentTransaction = createPaymentTransaction(fine);
        return initiatePayment(paymentTransaction);
    }

    //ask member to click pay now and call external gateway to receive payment
    private boolean initiatePayment(PaymentTransaction paymentTransaction) {

        return true;
    }

    private PaymentTransaction createPaymentTransaction(Fine fine) {
        String paymentMode = getPaymentMode();
        PaymentTransaction paymentTransaction = buildPaymentTransaction(paymentMode);
        paymentTransaction.setPayableAmount(fine.getAmount());
        paymentTransaction.setPaymentDate(LocalDate.now());
        return paymentTransaction;
    }

    //get payment mode details from member
    private String getPaymentMode() {
        return "paymentMode";
    }

    //create payment transaction based on payment mode
    private PaymentTransaction buildPaymentTransaction(String paymentMode) {
        return new PaymentTransaction();
    }
}
