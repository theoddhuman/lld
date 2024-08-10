package com.subham.lld.librarymanagement.service;

import com.subham.lld.librarymanagement.model.book.BookReservation;
import com.subham.lld.librarymanagement.model.book.types.ReservationStatus;
import com.subham.lld.librarymanagement.repository.BookReservationRepository;

import java.time.LocalDate;
import java.util.List;

/**
 * @author subham.paul
 */
public class BookReservationService {
    private BookReservationRepository bookReservationRepository;

    public BookReservationService(BookReservationRepository bookReservationRepository) {
        this.bookReservationRepository = bookReservationRepository;
    }

    public BookReservation addReservation(long bookId, long accountId) {
        BookReservation bookReservation = BookReservation.builder()
                .bookId(bookId)
                .accountId(accountId)
                .reservationDate(LocalDate.now())
                .status(ReservationStatus.WAITING)
                .build();
        return bookReservationRepository.saveOrUpdate(bookReservation);
    }

    public boolean cancelReservation(BookReservation bookReservation) {
        return bookReservationRepository.updateReservationStatus(bookReservation, ReservationStatus.CANCELLED);
    }

    public boolean completeReservation(BookReservation bookReservation) {
        return bookReservationRepository.updateReservationStatus(bookReservation, ReservationStatus.COMPLETED);
    }

    public List<BookReservation> getBookReservationsByStatus(long bookId, ReservationStatus status) {
        return bookReservationRepository.getReservationsByStatus(bookId, status);
    }
}
