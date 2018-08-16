package com.restaurant.service.impl;

import com.restaurant.dao.UserDao;
import com.restaurant.entity.User;
import com.restaurant.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User selectUserById(String userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public User verifyUser(String userId, String password) {
        return userDao.getUserByIdAndPassword(userId,password);
    }

    @Override
    public int saveUser(User user) {
        return userDao.insertUser(user);
    }

    @Override
    public int updateUser(User user) {
        return userDao.updateUser(user);
    }
}
