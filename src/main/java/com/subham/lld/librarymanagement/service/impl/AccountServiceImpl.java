package com.subham.lld.librarymanagement.service.impl;

import com.subham.lld.librarymanagement.model.account.Account;
import com.subham.lld.librarymanagement.repository.AccountRepository;
import com.subham.lld.librarymanagement.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author subham.paul
 */

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void resetPassword(long id) {

    }

    @Override
    public long register(Account account) {
        return accountRepository.addAccount(account);
    }

    @Override
    public Account login(String userName, String password) throws Exception {
        return accountRepository.login(userName, password);
    }

    @Override
    public void logout(long id) {

    }

    @Override
    public boolean cancelMembership(long id) {
        return false;
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.getAllMembers();
    }


}
