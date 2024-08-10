package com.subham.lld.librarymanagement.model.account;

import lombok.Data;

/**
 * @author subham.paul
 */

@Data
public class Person {
    private String name;

    private String email;

    private long mobileNumber;

    private Address address;
}
