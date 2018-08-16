package com.restaurant.controller;

import com.restaurant.entity.Item;
import com.restaurant.entity.User;
import com.restaurant.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
public class FavoriteItemController {
    @Autowired
    private FavoriteService favoriteService;
    @RequestMapping(value = "/setFavorite",method = RequestMethod.GET)
    @ResponseBody
    private Map<String,Object> setFavorite(HttpServletRequest request){
        Map<String,Object>modelMap = new HashMap<>();
        User user = (User)request.getSession().getAttribute("user");
        String itemId = request.getParameter("itemId");
        if(itemId == null || itemId.trim().equals("")){
            modelMap.put("success",false);
            modelMap.put("errMsg", "item id could not be empty");
            return modelMap;
        }
        int effectedNum = favoriteService.setFavoriteItem(user.getUserId(), itemId);
        modelMap.put("success",true);
        return modelMap;
    }

    @RequestMapping(value = "/unsetFavorite",method = RequestMethod.GET)
    @ResponseBody
    private Map<String,Object> unsetFavorite(HttpServletRequest request){
        Map<String,Object>modelMap = new HashMap<>();
        User user = (User)request.getSession().getAttribute("user");
        String itemId = request.getParameter("itemId");
        if(itemId == null || itemId.trim().equals("")){
            modelMap.put("success",false);
            modelMap.put("errMsg", "item id could not be empty");
            return modelMap;
        }
        int effectedNum = favoriteService.unsetFavoriteItem(user.getUserId(), itemId);
        modelMap.put("success",true);
        return modelMap;
    }

    @RequestMapping(value="/getFavorite", method = RequestMethod.GET)
    @ResponseBody
    private Map<String,Object> getFavorite(HttpServletRequest request){
        Map<String,Object>modelMap = new HashMap<>();
        User user = (User)request.getSession().getAttribute("user");
        Set<Item> itemSet = favoriteService.getFavoriteItems(user.getUserId());
        List<Item> itemList = new ArrayList<>();
        for(Item item : itemSet){
            itemList.add(item);
        }
        modelMap.put("success",true);
        modelMap.put("itemList",itemList);
        return modelMap;
    }
}
