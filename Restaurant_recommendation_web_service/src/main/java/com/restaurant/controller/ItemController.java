package com.restaurant.controller;

import com.restaurant.entity.Item;
import com.restaurant.entity.User;
import com.restaurant.externalApi.YelpAPI;
import com.restaurant.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ItemController {
    @Autowired
    ItemService itemService;
    @RequestMapping(value = "/search",method = RequestMethod.GET)
    @ResponseBody
    private Map<String,Object>searchItems(HttpServletRequest request){
        Map<String,Object>modelMap = new HashMap<>();
        Double lat = Double.parseDouble(request.getParameter("lat"));
        Double lon = Double.parseDouble(request.getParameter("lon"));
        if(lat == null || lon == null){
            modelMap.put("success",false);
            modelMap.put("errMsg", "could not load the location");
            return modelMap;
        }
        try{
            List<Item>itemList = itemService.searchItems(lat,lon,null);
            for(Item item : itemList){
                String addr = item.getAddress().replace('\"','\u0020').trim();
                String[]strs = addr.split(",");
                StringBuilder sb = new StringBuilder();
                for(String s : strs){
                    sb.append(s);
                    sb.append("</br>");
                }
                item.setAddress(sb.toString());
            }
            modelMap.put("itemList",itemList);
            modelMap.put("success",true);
        } catch (Exception e){
            modelMap.put("success",false);
            modelMap.put("errMsg",e.getMessage());
            return modelMap;
        }
        return modelMap;
    }

    @RequestMapping(value = "/recommendation", method = RequestMethod.GET)
    @ResponseBody
    private Map<String,Object>recommendation(HttpServletRequest request){
        Map<String,Object>modelMap = new HashMap<>();
        User user = (User)request.getSession().getAttribute("user");
        Double lat = Double.parseDouble(request.getParameter("lat"));
        Double lon = Double.parseDouble(request.getParameter("lon"));
        if(lat == null || lon == null){
            modelMap.put("success",false);
            modelMap.put("errMsg", "could not load the location");
            return modelMap;
        }
        try{
            List<Item>itemList = itemService.recommendItems(user.getUserId(), lat,lon);
            modelMap.put("itemList",itemList);
            modelMap.put("success",true);
        } catch (Exception e) {
            modelMap.put("success",false);
            e.printStackTrace();
            modelMap.put("errMsg",e.toString());
            return modelMap;
        }
        return modelMap;
    }

    @RequestMapping(value = "/carouselImg", method = RequestMethod.GET)
    @ResponseBody
    private Map<String,Object> carouselImg(HttpServletRequest request){
        Map<String,Object>modelMap = new HashMap<>();
        try {
            Double lat = Double.parseDouble(request.getParameter("lat"));
            Double lon = Double.parseDouble(request.getParameter("lon"));
            List<Item>itemList = new YelpAPI().search(lat,lon,null,8);
            modelMap.put("success",true);
            modelMap.put("itemList", itemList);
        } catch (Exception e){
            modelMap.put("success",false);
            modelMap.put("errMsg", "could not load the location" + e.getMessage());
            return modelMap;
        }
        return modelMap;
    }

}
