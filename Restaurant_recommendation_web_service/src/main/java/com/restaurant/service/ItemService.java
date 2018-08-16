package com.restaurant.service;

import com.restaurant.entity.Item;

import java.util.List;

public interface ItemService {
    List<Item>searchItems(double lat, double lon, String term);
    List<Item>recommendItems(String userId, double lat, double lon);
}
