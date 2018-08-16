package com.restaurant.dao;

import com.restaurant.BaseTest;
import com.restaurant.entity.Item;
import com.restaurant.externalApi.YelpAPI;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class ItemDaoTest extends BaseTest {
    @Autowired
    ItemDao itemDao;
    @Test
    public void testSelect(){
        List<String> itemIds = new ArrayList<>();
        itemIds.add("LTIho0Thpoh3A");
        itemIds.add("2FmoMFsQ2IKkp9m94Yg9Mg");
        itemIds.add("K6IdrX5prUvA5iAMCdQfGw");
        Set<Item> itemList = itemDao.selectItems(itemIds);
        System.out.println(itemList);
        assertEquals(itemIds.size(),3);
    }

    @Test
    public void testBatchInsert(){
        List<Item>searchItems = new YelpAPI().search(39.38, -123.08,null);
        itemDao.batchInsertItem(searchItems);
    }
}
