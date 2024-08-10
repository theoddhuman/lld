package com.subham.lld.librarymanagement.model.account;

import lombok.Data;

/**
 * @author subham.paul
 */

@Data
public class Address {
    private String street;

    private String city;

    private String state;

    private String zipCode;

    private String country;
}
