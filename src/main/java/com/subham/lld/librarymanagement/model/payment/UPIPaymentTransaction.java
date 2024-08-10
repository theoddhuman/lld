package com.subham.lld.librarymanagement.model.payment;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author subham.paul
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class UPIPaymentTransaction extends PaymentTransaction{
    private String upiId;
}
