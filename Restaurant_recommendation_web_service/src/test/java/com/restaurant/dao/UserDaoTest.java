package com.restaurant.dao;

import com.restaurant.BaseTest;
import com.restaurant.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;

public class UserDaoTest extends BaseTest{
    @Autowired
    UserDao userDao;

    @Test
    public void testgetUserById(){
        String userId = "1111";
        User user = userDao.getUserById(userId);
        System.out.println(user);
    }

    @Test
    public void testgetUserByIdAndPassword(){
        String userId = "1111";
        String password = "124";
        User user = userDao.getUserByIdAndPassword(userId, password);
        System.out.println(user);
    }

    @Test
    public void testInsert(){
        User user = new User();
        user.setUserId("1234");
        user.setFirstName("Yize");
        user.setLastName("Liu");
        user.setPassword("lyz950222");
        int effectedNum = userDao.insertUser(user);
        assertEquals(effectedNum,1);
    }

    @Test
    public void testUpdate(){
        User user = new User();
        user.setUserId("1111");
        user.setLastName("bryant");
        user.setPassword("1234");
        userDao.updateUser(user);
    }
}
