package com.subham.lld.librarymanagement.service.impl;

import com.subham.lld.librarymanagement.model.book.Book;
import com.subham.lld.librarymanagement.model.book.BookLending;
import com.subham.lld.librarymanagement.model.book.BookReservation;
import com.subham.lld.librarymanagement.model.book.Fine;
import com.subham.lld.librarymanagement.model.book.types.BookStatus;
import com.subham.lld.librarymanagement.model.book.types.FineStatus;
import com.subham.lld.librarymanagement.model.book.types.LendingStatus;
import com.subham.lld.librarymanagement.model.book.types.ReservationStatus;
import com.subham.lld.librarymanagement.repository.AccountRepository;
import com.subham.lld.librarymanagement.service.BookLendingService;
import com.subham.lld.librarymanagement.service.BookReservationService;
import com.subham.lld.librarymanagement.service.BookService;
import com.subham.lld.librarymanagement.service.NotificationService;
import com.subham.lld.librarymanagement.service.PaymentService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

/**
 * @author subham.paul
 */

@Service
public class MemberService extends AccountServiceImpl {
    private BookService bookService;

    private BookReservationService bookReservationService;

    private BookLendingService bookLendingService;

    private PaymentService paymentService;

    private NotificationService notificationService;

    private static final int MAXIMUM_BOOKS = 5;

    public MemberService(AccountRepository accountRepository, BookService bookService,
                         BookReservationService bookReservationService, BookLendingService bookLendingService,
                         PaymentService paymentService, NotificationService notificationService) {
        super(accountRepository);
        this.bookService = bookService;
        this.bookReservationService = bookReservationService;
        this.bookLendingService = bookLendingService;
        this.paymentService = paymentService;
        this.notificationService = notificationService;
    }

    public int getTotalCheckedOutBooks(long id) {
        List<BookLending> bookLendings = bookLendingService.getByAccountId(id);
        if(CollectionUtils.isEmpty(bookLendings)) {
            return 0;
        }
        return (int) bookLendings.stream()
                .filter(bookLending -> LendingStatus.LEND.equals(bookLending.getStatus()))
                .count();
    }

    public boolean reserveBook(long bookId, long accountId) {
        bookReservationService.addReservation(bookId, accountId);
        bookService.updateStatus(bookId, BookStatus.RESERVED);
        return true;
    }

    public boolean cancelReservation(long bookId, long accountId) {
        bookReservationService.cancelReservation(BookReservation.builder()
                .bookId(bookId)
                .accountId(accountId)
                .status(ReservationStatus.WAITING)
                .build());
        bookService.updateStatus(bookId, BookStatus.AVAILABLE);
        return true;
    }

    public BookLending checkOut(long bookId, long accountId) {
        if(getTotalCheckedOutBooks(accountId) >= MAXIMUM_BOOKS) {
            System.out.println("User crossed limit of books");
            return null;
        }
        Book book = bookService.getBook(bookId);
        if(BookStatus.LOANED.equals(book.getStatus()) || BookStatus.LOST.equals(book.getStatus())) {
            System.out.println("Book is not available");
            return null;
        }
        BookReservation bookReservation = null;
        if(BookStatus.RESERVED.equals(book.getStatus())) {
            List<BookReservation> bookReservations = bookReservationService
                    .getBookReservationsByStatus(bookId, ReservationStatus.WAITING);
            if(!CollectionUtils.isEmpty(bookReservations)) {
                //As first account will get preference.
                bookReservation = bookReservations.get(0);
                if(bookReservation.getAccountId() != accountId) {
                    System.out.println("Book is reserved by other user");
                    return null;
                }
            }
        }
        BookLending bookLending = bookLendingService.lendBook(bookId, accountId);
        bookService.updateStatus(bookId, BookStatus.LOANED);
        if(Objects.nonNull(bookReservation)) {
            bookReservationService.completeReservation(bookReservation);
        }
        return bookLending;
    }

    public boolean returnBook(long bookId, long accountId) {
        BookLending requiredBookLending = bookLendingService.getByBookIdAndAccountId(bookId, accountId)
                .stream().filter(bookLending -> LendingStatus.LEND.equals(bookLending.getStatus())).findFirst().get();

        //if due date crossed pay fine
        Fine fine = bookLendingService.checkFine(requiredBookLending);
        if(Objects.nonNull(fine)) {
            if(!paymentService.collectFine(fine)) {
                return false;
            }
            fine.setStatus(FineStatus.RETURN);
        }

        //return book
        bookLendingService.updateStatus(requiredBookLending.getId(), LendingStatus.RETURN);

        //if book is reserved by other member notify and mark book as reserved, else mark as available
        List<BookReservation> bookReservations = bookReservationService
                .getBookReservationsByStatus(bookId, ReservationStatus.WAITING);
        if(!CollectionUtils.isEmpty(bookReservations)) {
            bookService.updateStatus(bookId, BookStatus.RESERVED);
            BookReservation bookReservation = bookReservations.get(0);
            return notificationService.sendNotification(bookReservation);
        } else {
            return bookService.updateStatus(bookId, BookStatus.AVAILABLE);
        }
    }

    public boolean renewBook(long bookId, long accountId) {
        BookLending requiredBookLending = bookLendingService.getByBookIdAndAccountId(bookId, accountId)
                .stream().filter(bookLending -> LendingStatus.LEND.equals(bookLending.getStatus())).findFirst().get();

        Fine fine = bookLendingService.checkFine(requiredBookLending);
        if(Objects.nonNull(fine)) {
            if(!paymentService.collectFine(fine)) {
                return false;
            }
            fine.setStatus(FineStatus.RETURN);
        }

        bookLendingService.updateStatus(requiredBookLending.getId(), LendingStatus.RETURN);

        //if book is reserved by other member notify and mark book as reserved, else create a new lending transaction, book status remains same, loaned
        List<BookReservation> bookReservations = bookReservationService
                .getBookReservationsByStatus(bookId, ReservationStatus.WAITING);
        if(!CollectionUtils.isEmpty(bookReservations)) {
            bookService.updateStatus(bookId, BookStatus.RESERVED);
            BookReservation bookReservation = bookReservations.get(0);
            return notificationService.sendNotification(bookReservation);
        } else {
            return Objects.nonNull(bookLendingService.lendBook(bookId, accountId));
        }
    }
}
