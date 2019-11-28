package com.study.lab1.dao;

import com.study.lab1.model.Account;

public interface AccountDao {
    Account get(long id);

    void update(Account account);
}
