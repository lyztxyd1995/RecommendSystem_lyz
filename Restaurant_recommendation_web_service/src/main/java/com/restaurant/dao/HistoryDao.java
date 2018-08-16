package com.restaurant.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HistoryDao {
    int insertHistory(@Param("userId") String userId, @Param("itemId") String itemId);
    int deleteHistory(@Param("userId")String userId, @Param("itemId") String itemId);
    List<String> selectItemsId(@Param("userId") String userId);
    String selectOneItemId(@Param("userId") String userId, @Param("itemId") String itemId);
}
