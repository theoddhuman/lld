package com.subham.lld.librarymanagement.model.account;

import lombok.Data;

import java.time.LocalDate;

/**
 * @author subham.paul
 */

@Data
public class LibraryCard {
    private String cardNumber;

    private long userId;

    private boolean active;

    private LocalDate issueDate;
}
