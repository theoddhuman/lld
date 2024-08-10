package com.subham.lld.librarymanagement.repository;

import com.subham.lld.librarymanagement.model.book.Book;
import com.subham.lld.librarymanagement.model.book.BookLending;
import com.subham.lld.librarymanagement.model.book.BookReservation;
import com.subham.lld.librarymanagement.model.book.types.BookStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author subham.paul
 */

@Component
public class BookRepository {
    private static int totalBooks=0;

    private static long id = 0;

    private static Map<Long, Book> bookCatalog;

    private static Map<Long, BookReservation> bookReservationCatalog;

    private static Map<Long, BookLending> bookLendingCatalog;

    /*******indexing for searching **********/
    private static Map<String, List<Long>> titleCatalog;

    //ignoring this search
    private static Map<String, List<Long>> authorCatalog;

    private static Map<String, List<Long>> subjectCatalog;

    //ignoring this search
    private static Map<LocalDate, List<Long>> publicationDateCatalog;
    /*******************/

    public BookRepository() {
        bookCatalog = new HashMap<>();
        bookReservationCatalog = new HashMap<>();
        bookLendingCatalog = new HashMap<>();
        titleCatalog = new HashMap<>();
        authorCatalog = new HashMap<>();
        subjectCatalog = new HashMap<>();
        publicationDateCatalog = new HashMap<>();
    }

    public Book saveOrUpdate(Book book) {
        if(book.getId() == 0) {
            book.setId(++id);
        }
        bookCatalog.put(book.getId(), book);
        List<Long> titles = titleCatalog.getOrDefault(book.getTitle(), new ArrayList<>());
        titles.add(book.getId());
        titleCatalog.put(book.getTitle(), titles);
        List<Long> subjects = subjectCatalog.getOrDefault(book.getTitle(), new ArrayList<>());
        subjects.add(book.getId());
        subjectCatalog.put(book.getSubject(), subjects);
        return book;
    }

    public Book get(long id) {
        return bookCatalog.get(id);
    }

    public List<Book> getAll() {
        return bookCatalog.values().stream().toList();
    }

    public boolean delete(long id) {
        Book book = bookCatalog.get(id);
        bookCatalog.remove(id);
        titleCatalog.get(book.getTitle()).remove(id);
        authorCatalog.get(book.getSubject()).remove(id);
        return true;
    }

    public boolean updateStatus(long id, BookStatus status) {
        Book book = bookCatalog.get(id);
        book.setStatus(status);
        return true;
    }

    public List<Book> getBookByTitle(String title) {
        List<Long> bookIds = titleCatalog.get(title);
        return bookIds.stream().map(id -> bookCatalog.get(id)).collect(Collectors.toList());
    }

    public List<Book> getBookBySubject(String subject) {
        List<Long> bookIds = subjectCatalog.get(subject);
        return bookIds.stream().map(id -> bookCatalog.get(id)).collect(Collectors.toList());
    }
}
