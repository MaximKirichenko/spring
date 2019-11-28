package com.study.lab1.dao;

import com.study.lab1.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserCacheDaoImpl implements UserDao{
    private Map<Long, User> usersCache;

    @Autowired
    private MockDataSource mockDataSource;

    @PostConstruct
    private void postConstruct(){
        usersCache = new HashMap<>();
        List<User> users = mockDataSource.getUsers();
        for (User user : users) {
            usersCache.put(user.getId(), user);
        }
    }


    @Override
    public User getUser(long id) {
        return usersCache.get(id);
    }
}
