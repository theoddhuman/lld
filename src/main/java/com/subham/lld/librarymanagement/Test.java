package com.subham.lld.librarymanagement;

import com.subham.lld.librarymanagement.model.account.Account;
import com.subham.lld.librarymanagement.model.account.Member;
import com.subham.lld.librarymanagement.model.book.Book;
import com.subham.lld.librarymanagement.model.book.types.BookStatus;
import com.subham.lld.librarymanagement.model.book.types.ReservationStatus;
import com.subham.lld.librarymanagement.repository.AccountRepository;
import com.subham.lld.librarymanagement.repository.BookLendingRepository;
import com.subham.lld.librarymanagement.repository.BookRepository;
import com.subham.lld.librarymanagement.repository.BookReservationRepository;
import com.subham.lld.librarymanagement.service.AccountService;
import com.subham.lld.librarymanagement.service.BookLendingService;
import com.subham.lld.librarymanagement.service.BookReservationService;
import com.subham.lld.librarymanagement.service.BookService;
import com.subham.lld.librarymanagement.service.NotificationService;
import com.subham.lld.librarymanagement.service.PaymentService;
import com.subham.lld.librarymanagement.service.impl.AccountServiceImpl;
import com.subham.lld.librarymanagement.service.impl.BookServiceImpl;
import com.subham.lld.librarymanagement.service.impl.LibrarianService;
import com.subham.lld.librarymanagement.service.impl.MemberService;

import java.time.LocalDate;

/**
 * @author subham.paul
 */
public class Test {

    public static void main(String[] args) {
        BookRepository bookRepository = new BookRepository();
        BookService bookService = new BookServiceImpl(bookRepository);

        BookReservationRepository bookReservationRepository = new BookReservationRepository();
        BookReservationService bookReservationService = new BookReservationService(bookReservationRepository);

        BookLendingRepository bookLendingRepository = new BookLendingRepository();
        BookLendingService bookLendingService = new BookLendingService(bookLendingRepository);

        PaymentService paymentService = new PaymentService();
        NotificationService notificationService = new NotificationService();

        AccountRepository accountRepository = new AccountRepository();

        MemberService memberService = new MemberService(accountRepository, bookService,
                bookReservationService, bookLendingService, paymentService, notificationService);
        LibrarianService librarianService = new LibrarianService(accountRepository, bookService);

        AccountService accountService = new AccountServiceImpl(accountRepository);

        /********* Registering accounts **************/
        Member member1 = createMember("subham");
        Member member2 = createMember("rahul");
        Account librarian = createLibrarian("dinesh");

        memberService.register(member1);
        memberService.register(member2);
        librarianService.register(librarian);
        //System.out.println(accountService.getAllAccounts());

        /*********************** Adding books to library **********************/
        Book book1 = createBook("ABC", "Physics");
        Book book2 = createBook("ABC", "Physics");
        Book book3 = createBook("DEF", "Chemistry");
        Book book4 = createBook("DEF", "Chemistry");
        Book book5 = createBook("YO", "Mathematics");
        Book book6 = createBook("YOX", "Mathematics");

        librarianService.addBook(book1);
        librarianService.addBook(book2);
        librarianService.addBook(book3);
        librarianService.addBook(book4);
        librarianService.addBook(book5);

        /********************** reserve book ***********************************/
        memberService.reserveBook(book1.getId(), member1.getId());
        memberService.reserveBook(book1.getId(), member2.getId());

        System.out.println(bookService.searchByTitle("ABC"));
        System.out.println(bookReservationService.getBookReservationsByStatus(book1.getId(), ReservationStatus.WAITING));


//        memberService.cancelReservation(book1.getId(), member1.getId());
//        System.out.println(bookService.searchByTitle("ABC"));
//        System.out.println(bookReservationService.getBookReservationsByStatus(book1.getId(), ReservationStatus.WAITING));
//        System.out.println(bookReservationService.getBookReservationsByStatus(book1.getId(), ReservationStatus.CANCELLED));

        /************************ checkout book **************************/
        System.out.println(memberService.checkOut(book1.getId(), member1.getId()));
        System.out.println(memberService.checkOut(book2.getId(), member1.getId()));
        System.out.println(memberService.checkOut(book3.getId(), member1.getId()));
        System.out.println(memberService.checkOut(book4.getId(), member1.getId()));
        System.out.println(memberService.checkOut(book5.getId(), member1.getId()));
        System.out.println(memberService.checkOut(book6.getId(), member1.getId()));


        System.out.println(bookService.searchByTitle("ABC"));
        System.out.println(bookReservationService.getBookReservationsByStatus(book1.getId(), ReservationStatus.COMPLETED));
        System.out.println(bookLendingService.getByAccountId(member1.getId()));

        /************************ return book **************************/
        memberService.returnBook(book1.getId(), member1.getId());

        System.out.println(bookService.searchByTitle("ABC"));
        System.out.println(bookReservationService.getBookReservationsByStatus(book1.getId(), ReservationStatus.WAITING));
        System.out.println(bookLendingService.getByAccountId(member1.getId()));
    }

    private static Book createBook(String title, String subject) {
        Book book = new Book();
        book.setTitle(title);
        book.setSubject(subject);
        book.setRackNo(1);
        book.setStatus(BookStatus.AVAILABLE);
        return book;
    }

    private static Member createMember(String userName) {
        Member member = new Member();
        member.setDateOfMembership(LocalDate.now());
        member.setPassword("xyz");
        member.setUserName(userName);
        return member;
    }

    private static Account createLibrarian(String userName) {
        Account member = new Account();
        member.setPassword("xyz");
        member.setUserName(userName);
        return member;
    }
}
