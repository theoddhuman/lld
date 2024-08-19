package com.subham.lld.parkinglot.model.payment;

import lombok.Data;

/**
 * @author subham.paul
 */

@Data
public class CCPaymentModeDetails {
    private String cardNo;

    private int cvv;

    private String expiryDate;
}
