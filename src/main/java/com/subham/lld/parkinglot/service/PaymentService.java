package com.subham.lld.parkinglot.service;

import com.subham.lld.parkinglot.model.parking.type.PayProcessor;
import com.subham.lld.parkinglot.model.payment.CCPaymentModeDetails;
import com.subham.lld.parkinglot.model.payment.PaymentTransaction;
import com.subham.lld.parkinglot.model.parking.ParkingTicket;
import com.subham.lld.parkinglot.model.payment.UPIPaymentModeDetails;
import com.subham.lld.parkinglot.model.payment.types.PaymentMode;
import com.subham.lld.parkinglot.model.payment.types.PaymentStatus;
import com.subham.lld.parkinglot.repository.PaymentTransactionRepository;

import java.time.LocalDate;
import java.util.List;

/**
 * @author subham.paul
 */
public class PaymentService {
    private final PaymentTransactionRepository paymentTransactionRepository = new PaymentTransactionRepository();

    /**
     * Create payment transaction
     */
    public <T> PaymentTransaction<T> buildTransaction(ParkingTicket parkingTicket, T paymentModeDetails, long collectorId) {
        PaymentTransaction<T> paymentTransaction = createPaymentTransaction(parkingTicket, paymentModeDetails);
        if(collectorId != 0) {
            paymentTransaction.setCollectedBy(PayProcessor.PARKING_ATTENDANT);
            paymentTransaction.setCollectorId(collectorId);
        } else {
            paymentTransaction.setCollectedBy(PayProcessor.CUSTOMER);
        }
        return paymentTransactionRepository.save(paymentTransaction);
    }

    /**
     * ask member to click pay now and call external gateway to receive payment
     */
    public  <T> boolean initiatePayment(PaymentTransaction<T> paymentTransaction) {
        System.out.println("Payment Successful! Open the Gate");
        paymentTransactionRepository.updateStatus(paymentTransaction.getId(), PaymentStatus.COMPLETED);
        return true;
    }

    private <T> PaymentTransaction<T> createPaymentTransaction(ParkingTicket parkingTicket, T paymentModeDetails) {
        PaymentTransaction<T> paymentTransaction = new PaymentTransaction<>();
        paymentTransaction.setPaymentModeDetails(paymentModeDetails);
        paymentTransaction.setPayableAmount(parkingTicket.getPayableAmount());
        paymentTransaction.setStatus(PaymentStatus.INITIALIZED);
        if(paymentModeDetails instanceof CCPaymentModeDetails) {
            paymentTransaction.setPaymentMode(PaymentMode.CC);
        } else if (paymentModeDetails instanceof UPIPaymentModeDetails) {
            paymentTransaction.setPaymentMode(PaymentMode.UPI);
        }
        paymentTransaction.setPaymentDate(LocalDate.now());
        return paymentTransaction;
    }

    public List<PaymentTransaction<?>> viewTransactions() {
        return paymentTransactionRepository.get();
    }
}
