package com.subham.lld.librarymanagement.model.notification;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author subham.paul
 */

@EqualsAndHashCode(callSuper = true)
@Data
public class EmailNotification extends Notification {
    private String emailId;
}
