package com.restaurant.service;

import com.restaurant.BaseTest;
import com.restaurant.entity.Item;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class ItemServiceTest extends BaseTest {
    @Autowired
    ItemService itemService;

    @Test
    public void testsearchItems(){
        itemService.searchItems(39.38, -123.08,null);
    }

    @Test
    public void testRecommend(){
        List<Item>itemList = itemService.recommendItems("1111",40.38, -124.08);
        System.out.println(itemList.size());
        System.out.println(itemList);
    }
}
