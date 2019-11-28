package com.study.lab1.dao;

import com.study.lab1.dao.mapper.AccountRowMapper;
import com.study.lab1.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class AccountDaoH2Impl extends JdbcDaoSupport implements AccountDao {

    @Autowired
    private AccountRowMapper accountRowMapper;

    @PostConstruct
    private void postConstruct() {
        getJdbcTemplate().update("insert into account(id, balance) value (?, ?)", preparedStatement -> {
            for (int i = 1; i < 5; i++) {
                double balance = new BigDecimal(1000 * Math.random())
                        .setScale(2, RoundingMode.UP).doubleValue();
                preparedStatement.setLong(1, i);
                preparedStatement.setDouble(2, balance);
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        });
    }

    @Override
    public Account get(long id) {
        return getJdbcTemplate().query("SELECT * FROM account WHERE id = ?",
                preparedStatement -> preparedStatement.setLong(1, id),
                accountRowMapper
        ).get(0);
    }

    @Override
    public void update(Account account) {
        getJdbcTemplate().update("update account SET balance = ? where id= ?", preparedStatement -> {
            preparedStatement.setDouble(1, account.getBalance());
            preparedStatement.setLong(2, account.getId());
        });
    }
}
