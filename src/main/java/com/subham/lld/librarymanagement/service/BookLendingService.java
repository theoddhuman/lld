package com.subham.lld.librarymanagement.service;

import com.subham.lld.librarymanagement.model.book.BookLending;
import com.subham.lld.librarymanagement.model.book.Fine;
import com.subham.lld.librarymanagement.model.book.types.FineStatus;
import com.subham.lld.librarymanagement.model.book.types.LendingStatus;
import com.subham.lld.librarymanagement.repository.BookLendingRepository;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * @author subham.paul
 */
public class BookLendingService {
    private static final double finePerDay = 10.0;

    private static long fineId = 0;

    private BookLendingRepository bookLendingRepository;

    public BookLendingService(BookLendingRepository bookLendingRepository) {
        this.bookLendingRepository = bookLendingRepository;
    }

    public BookLending lendBook(long bookId, long accountId) {
        BookLending bookLending = BookLending.builder()
                .bookId(bookId)
                .accountId(accountId)
                .issueDate(LocalDate.now())
                .dueDate(LocalDate.now().plusDays(10))
                .status(LendingStatus.LEND)
                .build();
        return bookLendingRepository.saveOrUpdate(bookLending);
    }

    public List<BookLending> getByBookId(long bookId) {
        return bookLendingRepository.getByBookId(bookId);
    }

    public List<BookLending> getByAccountId(long accountId) {
        return bookLendingRepository.getByAccountId(accountId);
    }

    public List<BookLending> getByBookIdAndAccountId(long bookId, long accountId) {
        return bookLendingRepository.getByBookIdAndAccountId(bookId, accountId);
    }

    public boolean updateStatus(long id, LendingStatus status) {
        return bookLendingRepository.updateStatus(id, status);
    }

    public Fine checkFine(BookLending bookLending) {
        long dueDays = ChronoUnit.DAYS.between( bookLending.getDueDate(),LocalDate.now());
        if(dueDays > 0) {
            Fine fine = new Fine();
            fine.setId(++fineId);
            fine.setAmount(dueDays * finePerDay);
            fine.setBookLendingId(bookLending.getId());
            fine.setStatus(FineStatus.PENDING);
            bookLending.setFine(fine);
            bookLendingRepository.saveOrUpdate(bookLending);
            return fine;
        }
        return null;
    }

}
