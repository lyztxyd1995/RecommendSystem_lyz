package com.restaurant.service;

import com.restaurant.entity.Item;

import java.util.Set;

public interface FavoriteService {
    int setFavoriteItem(String userId, String itemId);
    int unsetFavoriteItem(String userId, String itemId);
    Set<Item>getFavoriteItems(String userId);
}
