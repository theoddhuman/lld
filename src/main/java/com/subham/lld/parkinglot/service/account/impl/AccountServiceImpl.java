package com.subham.lld.parkinglot.service.account.impl;

import com.subham.lld.parkinglot.model.account.Account;
import com.subham.lld.parkinglot.repository.AccountRepository;
import com.subham.lld.parkinglot.service.account.AccountService;

import java.util.List;

/**
 * @author subham.paul
 */
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    public AccountServiceImpl() {
        this.accountRepository = new AccountRepository();
    }

    @Override
    public Account addAccount(Account account) {
        return accountRepository.saveOrUpdate(account);
    }

    @Override
    public void deleteAccount(long id) {
        accountRepository.delete(id);
    }

    @Override
    public List<Account> viewAll() {
        return accountRepository.getAll();
    }

    @Override
    public Account view(long id) {
        return accountRepository.get(id);
    }

    @Override
    public Account login(String userName, String password) {
        return accountRepository.login(userName, password);
    }

    @Override
    public void logout(String userName) {
        accountRepository.logout(userName);
    }
}
