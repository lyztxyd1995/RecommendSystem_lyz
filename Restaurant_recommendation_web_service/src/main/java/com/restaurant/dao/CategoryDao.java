package com.restaurant.dao;

import com.restaurant.entity.Category;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

public interface CategoryDao {
    int batchInsertCategory(List<Category>categoryList);
    Set<String> selectCategories(@Param("itemId") String itemId);
    List<Category> selectCategoriesByListItems(@Param("itemIds") List<String>itemIds);
}
