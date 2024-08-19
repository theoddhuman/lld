package com.subham.lld.parkinglot.repository;

import com.subham.lld.parkinglot.model.account.Account;
import com.subham.lld.parkinglot.model.account.type.AccountStatus;
import com.subham.lld.parkinglot.model.account.type.LoginStatus;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author subham.paul
 */
public class AccountRepository {
    private static long id = 0;
    private static final Map<Long, Account> accountMap = new HashMap<>();
    private static final Map<String, Long> userNameToIdMap = new HashMap<>();

    public Account saveOrUpdate(Account account) {
        if(account.getId() == null) {
            account.setId(++id);
            account.setStatus(AccountStatus.ACTIVE);
            account.setCreationTime(LocalDate.now());
            account.setLoginStatus(LoginStatus.LOGGED_OUT);
        }
        accountMap.put(account.getId(), account);
        userNameToIdMap.put(account.getUserName(), account.getId());
        return account;
    }

    public void delete(long id) {
        Account account = accountMap.get(id);
        accountMap.remove(id);
        userNameToIdMap.remove(account.getUserName());
    }

    public List<Account> getAll() {
        return accountMap.values().stream().toList();
    }

    public Account get(long id) {
        return accountMap.get(id);
    }

    public Account login(String userName, String password) {
        long id = userNameToIdMap.get(userName);
        Account account = accountMap.get(id);
        if(Objects.equals(password, account.getPassword())) {
            account.setLastLoginTime(LocalDate.now());
            account.setLoginStatus(LoginStatus.LOGGED_IN);
            return account;
        }
        return null;
    }

    public void logout(String userName) {
        long id = userNameToIdMap.get(userName);
        Account account = accountMap.get(id);
        account.setLoginStatus(LoginStatus.LOGGED_OUT);
    }
}
