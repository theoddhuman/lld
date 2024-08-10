package com.subham.lld.librarymanagement.service;

import com.subham.lld.librarymanagement.model.book.Book;
import com.subham.lld.librarymanagement.model.book.types.BookStatus;

import java.time.LocalDate;
import java.util.List;

/**
 * @author subham.paul
 */


public interface BookService {
    Book addBook(Book book);

    List<Book> getAll();

    Book updateBook(Book book);

    boolean updateStatus(long id, BookStatus status);

    boolean removeBook(long id);

    Book getBook(long id);

    List<Book> searchByTitle(String title);

    List<Book> searchByAuthor(String title);

    List<Book> searchBySubject(String subject);

    List<Book> searchByPublicationDate(LocalDate publicationDate);
}
