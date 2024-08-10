package com.subham.lld.librarymanagement.repository;

import com.subham.lld.librarymanagement.model.account.Account;
import com.subham.lld.librarymanagement.model.account.types.AccountStatus;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author subham.paul
 */

@Component
public class AccountRepository {
    private static long id = 0l;
    private static Map<Long, Account> accountCatalog;
    private static Map<String, Long> userNameCatalog;

    public AccountRepository() {
        accountCatalog = new HashMap<>();
        userNameCatalog = new HashMap<>();
    }

    public long addAccount(Account account) {
        account.setId(++id);
        account.setStatus(AccountStatus.ACTIVE);
        accountCatalog.put(id, account);
        userNameCatalog.put(account.getUserName(), id);
        return id;
    }

    public boolean updateAccount(Account account) {
        Account oldAccount = accountCatalog.get(account.getId());
        String oldUserName = oldAccount.getUserName();
        accountCatalog.put(account.getId(), account);
        userNameCatalog.remove(oldUserName);
        userNameCatalog.put(account.getUserName(), account.getId());
        return true;
    }

    public Account getAccount(long id) {
        return accountCatalog.get(id);
    }

    public List<Account> getAllMembers() {
        return accountCatalog.values().stream().toList();
    }

    public Account login(String userName, String password) throws Exception {
        Long id = userNameCatalog.get(userName);
        Account account = accountCatalog.get(id);
        if(account.getPassword().equals(password)) {
            return account;
        }
        throw new Exception("Invalid user or password");
    }
}
