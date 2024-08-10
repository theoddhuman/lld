package com.subham.lld.librarymanagement.service;

import com.subham.lld.librarymanagement.model.account.Account;

import java.util.List;

/**
 * @author subham.paul
 */
public interface AccountService {
    void resetPassword(long id);

    long register(Account account);

    Account login(String userName, String password) throws Exception;

    void logout(long id);

    boolean cancelMembership(long id);

    List<Account> getAllAccounts();
}
