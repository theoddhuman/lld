package com.subham.lld.librarymanagement.model.payment;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author subham.paul
 */

@EqualsAndHashCode(callSuper = true)
@Data
public class CCPaymentTransaction extends PaymentTransaction {
    private String cardNo;

    private int cvv;
}
