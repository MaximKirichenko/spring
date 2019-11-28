package com.study.lab1.dao;

import com.study.lab1.model.Account;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

public class AccountCacheDaoIml implements AccountDao {
    private Map<Long, Account> accountCache;

    @Autowired
    private MockDataSource mockDataSource;

    @PostConstruct
    private void PostConstruct() {
        accountCache = new HashMap<>();
        for (Account account : mockDataSource.getAccounts()) {
            accountCache.put(account.getId(), account);
        }
    }

    @Override
    public Account get(long id) {
        return accountCache.get(id);
    }

    @Override
    public void update(Account account) {
        mockDataSource.updateAccount(account);
        accountCache.put(account.getId(), account);
    }
}
