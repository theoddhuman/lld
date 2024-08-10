package com.subham.lld.librarymanagement.model.book;

import com.subham.lld.librarymanagement.model.book.types.FineStatus;
import lombok.Data;

/**
 * @author subham.paul
 */

@Data
public class Fine {
    long id;

    long bookLendingId;

    double amount;

    FineStatus status;
}
