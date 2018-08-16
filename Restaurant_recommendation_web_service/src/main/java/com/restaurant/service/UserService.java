package com.restaurant.service;

import com.restaurant.entity.User;

public interface UserService {
    User selectUserById(String userId);
    User verifyUser(String userId, String password);
    int saveUser(User user);
    int updateUser(User user);
}
