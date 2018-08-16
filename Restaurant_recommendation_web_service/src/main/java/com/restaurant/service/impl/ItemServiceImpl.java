package com.restaurant.service.impl;

import com.restaurant.dao.CategoryDao;
import com.restaurant.dao.HistoryDao;
import com.restaurant.dao.ItemDao;
import com.restaurant.entity.Category;
import com.restaurant.entity.Item;
import com.restaurant.externalApi.YelpAPI;
import com.restaurant.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    ItemDao itemDao;
    @Autowired
    CategoryDao categoryDao;
    @Autowired
    HistoryDao historyDao;
    @Override
    @Transactional
    public List<Item> searchItems(double lat, double lon, String term) {
        List<Item>items = new YelpAPI().search(lat,lon,term);
        try{
            List<Category>categoryList = new ArrayList<>();
            for(Item item:items){
                for(String categoryStr: item.getCategories()){
                    Category category = new Category();
                    category.setItemId(item.getItemId());
                    category.setCategory(categoryStr);
                    categoryList.add(category);
                }
            }
            itemDao.batchInsertItem(items);
            categoryDao.batchInsertCategory(categoryList);
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException("fail to insert data to database");
        }
        return items;
    }

    @Override
    @Transactional
    public List<Item> recommendItems(String userId, double lat, double lon) {
        try {
            //1. get the item ids of user's favorite items
            List<String> favoriteItemIdsList = historyDao.selectItemsId(userId);
            Set<String> favoriteItemIds = new HashSet<>();
            for (String itemId : favoriteItemIdsList) {
                favoriteItemIds.add(itemId);
            }
            //2. get all user's favorite items' categories according to the item ids
            List<Category> categoryList = categoryDao.selectCategoriesByListItems(favoriteItemIdsList);
            Set<String> allCategories = new HashSet<>();
            for (Category category : categoryList) {
                allCategories.add(category.getCategory());
            }
            //3.get nearby restaurants
            Set<Item> recommendItems = new HashSet<>();
            for (String category : allCategories) {
                List<Item> items = searchItems(lat, lon, category);
                recommendItems.addAll(items);
            }
            //4. recommend according to items' categories and distance

            //remove items that has been already saved by the user
            List<Item> filteredItems = new ArrayList<>();
            for (Item item : recommendItems) {
                if (!favoriteItemIds.contains(item.getItemId())) {
                    filteredItems.add(item);
                }
            }

            if(filteredItems.size() == 0 || recommendItems == null){
                return filteredItems;
            }
            //perform ranking of items based on distance

            Collections.sort(filteredItems, new Comparator<Item>() {
                @Override
                public int compare(Item o1, Item o2) {
                    double rating1 = o1.getRating();
                    double rating2 = o2.getRating();
                    if(rating1 == rating2){
                        double distance1 = getDistance(o1.getLatitude(), o1.getLongitude(), lat, lon);
                        double distance2 = getDistance(o2.getLatitude(), o2.getLongitude(), lat, lon);
                        return (int) (distance1 - distance2);
                    } else {
                        return (int)(rating2 - rating1);
                    }
                }
            });
            return filteredItems.subList(0,20);
        } catch (Exception e){
            throw new RuntimeException();
        }
    }

    private static double getDistance(double lat1, double lon1, double lat2, double lon2) {
        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;
        double a = Math.sin(dlat / 2 / 180 * Math.PI) * Math.sin(dlat / 2 / 180 * Math.PI)
                + Math.cos(lat1 / 180 * Math.PI) * Math.cos(lat2 / 180 * Math.PI)
                * Math.sin(dlon / 2 / 180 * Math.PI) * Math.sin(dlon / 2 / 180 * Math.PI);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        // Radius of earth in miles.
        double R = 3961;
        return R * c;
    }
}
