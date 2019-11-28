package com.study.lab1.dao;

import com.study.lab1.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao{

    @Autowired
    private MockDataSource dataSource;

    public User getUser(long id) {
        long start = System.currentTimeMillis();
        User user = dataSource.getUser(id);
        System.out.println("getting user took : " + (System.currentTimeMillis() - start));
        return user;

    }

}
