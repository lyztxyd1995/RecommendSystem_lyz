package com.restaurant.dao;

import com.restaurant.entity.User;
import org.apache.ibatis.annotations.Param;

import javax.jws.soap.SOAPBinding;

public interface UserDao {
    User getUserById(@Param("userId") String userId);

    User getUserByIdAndPassword(@Param("userId") String userId, @Param("password")String password);

    int insertUser(User user);

    int updateUser(User user);
}
