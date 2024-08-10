package com.subham.lld.librarymanagement.service;

import com.subham.lld.librarymanagement.model.book.BookReservation;
import com.subham.lld.librarymanagement.model.notification.Notification;

/**
 * @author subham.paul
 */
public class NotificationService {

    public boolean sendNotification(BookReservation bookReservation) {
        Notification emailNotification = createNotification("email", bookReservation);
        Notification smsNotification = createNotification("sms", bookReservation);
        //send email
        //send sms
        System.out.println("Book " + bookReservation.getBookId() + " is reserved by user " + bookReservation.getAccountId());
        return true;
    }

    //create notification based on notify mode
    private Notification createNotification(String notifyMode, BookReservation bookReservation) {
        return new Notification();
    }
}
