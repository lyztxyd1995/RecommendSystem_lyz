package com.restaurant.service;

import com.restaurant.BaseTest;
import com.restaurant.entity.Item;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class FavoriteServiceTest extends BaseTest{
    @Autowired
    FavoriteService favoriteService;

    @Test
    public void testgetFavoriteItems(){
        long startTime = System.currentTimeMillis();
        Set<Item> itemList = favoriteService.getFavoriteItems("1111");
        long endTime = System.currentTimeMillis();
        System.out.println("total time:" + (endTime - startTime));
        System.out.println(itemList);
    }
}
