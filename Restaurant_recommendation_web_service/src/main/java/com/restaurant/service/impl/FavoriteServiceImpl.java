package com.restaurant.service.impl;

import com.restaurant.dao.CategoryDao;
import com.restaurant.dao.HistoryDao;
import com.restaurant.dao.ItemDao;
import com.restaurant.entity.Category;
import com.restaurant.entity.Item;
import com.restaurant.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class FavoriteServiceImpl implements FavoriteService {
    @Autowired
    private CategoryDao categoryDao;
    @Autowired
    private ItemDao itemDao;
    @Autowired
    private HistoryDao historyDao;


    @Override
    @Transactional
    public int setFavoriteItem(String userId, String itemId) {
        String searchItemId = historyDao.selectOneItemId(userId,itemId);
        if(searchItemId != null){
            return 0;
        }
        return historyDao.insertHistory(userId,itemId);
    }

    @Override
    public int unsetFavoriteItem(String userId, String itemId) {
        return historyDao.deleteHistory(userId,itemId);
    }

    @Override
    @Transactional
    public Set<Item> getFavoriteItems(String userId) {
        Set<Item>itemList = new HashSet<>();
        try{
            List<String> itemIds = historyDao.selectItemsId(userId);
            itemList = itemDao.selectItems(itemIds);
            List<Category>categoryList = categoryDao.selectCategoriesByListItems(itemIds);
            Map<String,Item>itemsMap = new HashMap<>();
            for(Item item : itemList){
                itemsMap.put(item.getItemId(), item);
            }
            for(Category category : categoryList){
                Item item = itemsMap.get(category.getItemId());
                if(item != null){
                    Set<String>categoriesSet = item.getCategories();
                    if(categoriesSet == null){
                        categoriesSet = new HashSet<>();
                        item.setCategories(categoriesSet);
                    }
                    categoriesSet.add(category.getCategory());
                }
            }
        } catch (Exception e){
            throw new RuntimeException();
        }
        return itemList;
    }


}
