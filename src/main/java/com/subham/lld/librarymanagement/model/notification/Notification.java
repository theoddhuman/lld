package com.subham.lld.librarymanagement.model.notification;

import lombok.Data;

import java.time.LocalDate;

/**
 * @author subham.paul
 */

@Data
public class Notification {
    private long id;

    private LocalDate createdOn;

    private String content;
}
