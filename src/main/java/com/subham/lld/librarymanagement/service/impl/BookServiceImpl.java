package com.subham.lld.librarymanagement.service.impl;

import com.subham.lld.librarymanagement.model.book.Book;
import com.subham.lld.librarymanagement.model.book.types.BookStatus;
import com.subham.lld.librarymanagement.repository.BookRepository;
import com.subham.lld.librarymanagement.service.BookService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * @author subham.paul
 */

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book addBook(Book book) {
        return bookRepository.saveOrUpdate(book);
    }

    @Override
    public List<Book> getAll() {
        return bookRepository.getAll();
    }

    @Override
    public Book updateBook(Book book) {
        return bookRepository.saveOrUpdate(book);
    }

    @Override
    public boolean updateStatus(long id, BookStatus status) {
        return bookRepository.updateStatus(id, status);
    }

    @Override
    public boolean removeBook(long id) {
        return bookRepository.delete(id);
    }

    @Override
    public Book getBook(long id) {
        return bookRepository.get(id);
    }

    @Override
    public List<Book> searchByTitle(String title) {
        return bookRepository.getBookByTitle(title);
    }

    @Override
    public List<Book> searchByAuthor(String title) {
        return null;
    }

    @Override
    public List<Book> searchBySubject(String subject) {
        return bookRepository.getBookBySubject(subject);
    }

    @Override
    public List<Book> searchByPublicationDate(LocalDate publicationDate) {
        return null;
    }
}
