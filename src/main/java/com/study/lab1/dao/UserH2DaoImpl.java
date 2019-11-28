package com.study.lab1.dao;

import com.study.lab1.dao.mapper.UserRowMapper;
import com.study.lab1.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

@Repository
public class UserH2DaoImpl extends JdbcDaoSupport implements UserDao {

    @Autowired
    private UserRowMapper userRowMapper;

    @PostConstruct
    private void postConstruct() {
        getJdbcTemplate().update("insert into user(id) value (?)", preparedStatement -> {
            for (int i = 1; i < 5; i++) {
                preparedStatement.setLong(1, i);
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        });
    }

    @Override
    public User getUser(long id) {
        return getJdbcTemplate()
                .query("SELECT * FROM user WHERE id = ?",
                        preparedStatement -> preparedStatement.setLong(1, id),
                        userRowMapper)
                .get(0);
    }
}
