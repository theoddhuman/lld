package com.subham.lld.librarymanagement.model.payment;

import lombok.Data;

import java.time.LocalDate;

/**
 * @author subham.paul
 */

@Data
public class PaymentTransaction {
    private double payableAmount;

    private LocalDate paymentDate;
}
