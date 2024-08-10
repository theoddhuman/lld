package com.subham.lld.librarymanagement.model.book;

import com.subham.lld.librarymanagement.model.book.types.LendingStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

/**
 * @author subham.paul
 */

@Data
@Builder
public class BookLending {
    private long id;

    private long bookId;

    private long accountId;

    private LocalDate issueDate;

    private LocalDate dueDate;

    private LendingStatus status;

    private Fine fine;
}
