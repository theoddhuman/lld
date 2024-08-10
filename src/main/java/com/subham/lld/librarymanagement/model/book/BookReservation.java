package com.subham.lld.librarymanagement.model.book;

import com.subham.lld.librarymanagement.model.book.types.ReservationStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

/**
 * @author subham.paul
 */

@Data
@Builder
public class BookReservation {
    private long bookId;

    private long accountId;

    private LocalDate reservationDate;

    private ReservationStatus status;
}
