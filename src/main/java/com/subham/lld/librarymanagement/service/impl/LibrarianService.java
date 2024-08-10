package com.subham.lld.librarymanagement.service.impl;

import com.subham.lld.librarymanagement.model.book.Book;
import com.subham.lld.librarymanagement.repository.AccountRepository;
import com.subham.lld.librarymanagement.service.BookService;
import org.springframework.stereotype.Service;

/**
 * @author subham.paul
 */

@Service
public class LibrarianService extends AccountServiceImpl{
    private BookService bookService;

    public LibrarianService(AccountRepository accountRepository, BookService bookService) {
        super(accountRepository);
        this.bookService = bookService;
    }

    public void addBook(Book book) {
        bookService.addBook(book);
    }

    public boolean blockMember(long id) {
        return false;
    }

    public boolean unblockMember(long id) {
        return false;
    }

}
