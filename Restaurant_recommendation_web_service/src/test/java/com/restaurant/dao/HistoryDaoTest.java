package com.restaurant.dao;

import com.restaurant.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class HistoryDaoTest extends BaseTest {
    @Autowired
    HistoryDao historyDao;
    @Test
    public void testInsertAndDelete(){
        String userId = "1111";
        String itemId = "1";
        int effectedNum = historyDao.insertHistory(userId,itemId);
        assertEquals(effectedNum,1);
        effectedNum = historyDao.deleteHistory(userId,itemId);
        assertEquals(effectedNum,1);
        effectedNum = historyDao.deleteHistory(userId,"1424242");
        assertEquals(effectedNum,0);
    }

    @Test
    public void testSelect(){
        List<String>result = historyDao.selectItemsId("1111");
        System.out.println(result.size());
        historyDao.deleteHistory("1111", "1");
    }

    @Test
    public void testStr(){
        String str = " \" Hello world \" ";
        System.out.println(str);
        String str1 = str.replace('\"','\u0020').trim();
        System.out.println(str1);
    }
}
