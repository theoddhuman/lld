package com.subham.lld.librarymanagement.repository;

import com.subham.lld.librarymanagement.model.book.BookLending;
import com.subham.lld.librarymanagement.model.book.types.LendingStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author subham.paul
 */
public class BookLendingRepository {
    private static long id = 0;
    private static Map<Long, BookLending> bookLendingCatalog;
    private static Map<Long, List<Long>> bookLendingAccountCatalog;
    private static Map<Long, List<Long>> bookLendingBookCatalog;

    public BookLendingRepository() {
        bookLendingAccountCatalog = new HashMap<>();
        bookLendingBookCatalog = new HashMap<>();
        bookLendingCatalog = new HashMap<>();
    }

    public BookLending saveOrUpdate(BookLending bookLending) {
        if(bookLending.getId() == 0) {
            bookLending.setId(++id);
            List<Long> bookLendings = bookLendingBookCatalog.getOrDefault(bookLending.getBookId(), new ArrayList<>());
            bookLendings.add(bookLending.getId());
            bookLendingBookCatalog.put(bookLending.getBookId(), bookLendings);
            List<Long> bookLendings1 = bookLendingAccountCatalog.getOrDefault(bookLending.getAccountId(), new ArrayList<>());
            bookLendings1.add(bookLending.getId());
            bookLendingAccountCatalog.put(bookLending.getAccountId(), bookLendings1);
        }
        bookLendingCatalog.put(bookLending.getBookId(), bookLending);
        return bookLending;
    }

    public boolean updateStatus(long id, LendingStatus status) {
        bookLendingCatalog.get(id).setStatus(status);
        return true;
    }

    public List<BookLending> getByBookIdAndAccountId(long bookId, long accountId) {
        return getByAccountId(accountId).stream()
                .filter(bookLending -> bookLending.getBookId() == bookId)
                .collect(Collectors.toList());
    }

    public List<BookLending> getByBookId(long bookId) {
        return bookLendingBookCatalog.getOrDefault(bookId, new ArrayList<>())
                .stream()
                .map(id -> bookLendingCatalog.get(id))
                .collect(Collectors.toList());
    }

    public List<BookLending> getByAccountId(long accountId) {
        return bookLendingAccountCatalog.getOrDefault(accountId, new ArrayList<>())
                .stream()
                .map(id -> bookLendingCatalog.get(id))
                .collect(Collectors.toList());
    }
}
