package com.restaurant.dao;

import com.restaurant.entity.Item;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ItemDao {
    int insertItem(Item item);
    int batchInsertItem(List<Item> itemList);
    Set<Item> selectItems(@Param("itemIds") List<String>itemIds);
}
