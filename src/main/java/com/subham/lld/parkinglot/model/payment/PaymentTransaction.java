package com.subham.lld.parkinglot.model.payment;

import com.subham.lld.parkinglot.model.parking.type.PayProcessor;
import com.subham.lld.parkinglot.model.payment.types.PaymentMode;
import com.subham.lld.parkinglot.model.payment.types.PaymentStatus;
import lombok.Data;

import java.time.LocalDate;

/**
 * @author subham.paul
 */

@Data
public class PaymentTransaction<T> {
    private long id;

    private double payableAmount;

    private LocalDate paymentDate;

    private PaymentStatus status;

    private PaymentMode paymentMode;

    private PayProcessor collectedBy;

    private Long collectorId;

    private T paymentModeDetails;
}
