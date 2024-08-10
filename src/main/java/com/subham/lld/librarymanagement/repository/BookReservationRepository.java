package com.subham.lld.librarymanagement.repository;

import com.subham.lld.librarymanagement.model.book.BookReservation;
import com.subham.lld.librarymanagement.model.book.types.ReservationStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author subham.paul
 */
public class BookReservationRepository {
    //bookId -> reservationStatus -> (WAITING can have at max one book reservation
    private static Map<Long, Map<ReservationStatus, List<BookReservation>>> bookReservationCatalog;

    public BookReservationRepository() {
        bookReservationCatalog = new HashMap<>();
    }

    public BookReservation saveOrUpdate(BookReservation bookReservation) {
        Map<ReservationStatus, List<BookReservation>> bookReservationMap = bookReservationCatalog.getOrDefault(bookReservation.getBookId(), new HashMap<>());
        List<BookReservation> bookReservations = bookReservationMap.getOrDefault(bookReservation.getStatus(), new ArrayList<>());
        bookReservations.add(bookReservation);
        bookReservationMap.put(bookReservation.getStatus(), bookReservations);
        bookReservationCatalog.put(bookReservation.getBookId(), bookReservationMap);
        return bookReservation;
    }


    /**
     * remove reservation from current status and add to new status
     */
    public boolean updateReservationStatus(BookReservation bookReservation, ReservationStatus status) {
        Map<ReservationStatus, List<BookReservation>> bookReservationMap = bookReservationCatalog.get(bookReservation.getBookId());
        List<BookReservation> bookReservations = bookReservationMap.get(bookReservation.getStatus());
        bookReservations.removeIf(bookReservationEntry -> bookReservationEntry.getAccountId() == bookReservation.getAccountId());
        bookReservation.setStatus(status);
        List<BookReservation> statusBookReservations = bookReservationMap.getOrDefault(status, new ArrayList<>());
        statusBookReservations.add(bookReservation);
        bookReservationMap.put(status, statusBookReservations);
        return true;
    }

    public List<BookReservation> getReservationsByStatus(long bookId, ReservationStatus status) {
        return bookReservationCatalog.get(bookId).get(status);
    }
}
