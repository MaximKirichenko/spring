package com.study.lab1.dao;

import com.study.lab1.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDaoImpl implements AccountDao{

    @Autowired
    private MockDataSource dataSource;

    @Override
    public Account get(long id) {
        long start = System.currentTimeMillis();
        Account account = dataSource.getAccount(id);
        System.out.println("Getting account took : " + (System.currentTimeMillis() - start));
        return account;
    }

    @Override
    public void update(Account account) {
        long start = System.currentTimeMillis();
        dataSource.updateAccount(account);
        System.out.println("Updating account took : " + (System.currentTimeMillis() - start));
    }
}
