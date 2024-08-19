package com.subham.lld.parkinglot.service.account;

import com.subham.lld.parkinglot.model.account.Account;

import java.util.List;

/**
 * @author subham.paul
 */
public interface AccountService {
    Account addAccount(Account account);

    void deleteAccount(long id);

    List<Account> viewAll();

    Account view(long id);

    Account login(String userName, String password);

    void logout(String userName);
}
