package com.subham.lld.librarymanagement.model.account;

import com.subham.lld.librarymanagement.model.account.types.AccountStatus;
import com.subham.lld.librarymanagement.model.account.types.LoginStatus;
import lombok.Data;

/**
 * @author subham.paul
 */

@Data
public class Account {
    private long id;

    private String userName;

    private String password;

    private AccountStatus status;

    private LoginStatus loginStatus;

    private Person person;
}
