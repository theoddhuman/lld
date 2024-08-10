package com.subham.lld.librarymanagement.model.book;

import com.subham.lld.librarymanagement.model.book.types.BookStatus;
import lombok.Data;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.List;

/**
 * @author subham.paul
 */
@Data
public class Book {
    private long id;

    private String title;

    private List<String> authors;

    private LocalDate publicationDate;

    private String subject;

    private long rackNo;

    private BookStatus status;
}
