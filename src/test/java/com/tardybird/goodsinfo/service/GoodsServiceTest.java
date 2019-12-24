package com.tardybird.goodsinfo.service;

import com.tardybird.goodsinfo.dao.GoodsDao;
import com.tardybird.goodsinfo.domain.Goods;
import com.tardybird.goodsinfo.po.GoodsPo;
import static org.junit.Assert.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GoodsServiceTest {

    @Autowired
    GoodsService goodsService;

    @Autowired
    GoodsDao goodsDao;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

//    @Test
//    void getAllGoodsByConditions() {
//        String goodSn="drh-d0001";
//        String name ="《大吉大利》A(赠四方联）";
//        Integer page =1;
//        Integer limit=2;
//        System.out.println(goodsService.getAllGoodsByConditions(goodSn,name,page,limit));
//    }

    @Test
    void createGoods() {

        GoodsPo goods = new Goods();
        goods.setName("new goods");
        goods.setBeDeleted(false);
        GoodsPo goodsPo =goodsService.createGoods(goods);
        assertEquals(goods.getName(),goodsPo.getName());
        assertEquals(goods.getBeDeleted(),goodsPo.getBeDeleted());
    }
    @Test
    void getGoodsById() {
        Integer userId=1;
        Integer adminId=1;
       assertEquals("1",goodsService.getGoodsByIdUser(userId).getId());
       assertEquals("1",goodsService.getGoodsByIdAdmin(adminId).getId());
    }

//    @Test
//    void findGoodsByCategoryId() {
//        Integer categoryId=1;
//        Integer page=1;
//        Integer limit=5;
//        System.out.println(goodsService.findGoodsByCategoryId(categoryId,page,limit));
//    }
    
    @Test
    void updateGoods() {
        GoodsPo goods = new Goods();
        goods.setId(1);
        goods.setName("this is a NEW name");
        assertEquals(true,goodsService.updateGoods(goods));
    }

//    @Test
//    void deleteGood() {
//        Goods goods = new Goods();
//        goods.setId(1);
//
//        goodsService.deleteGood(goods.getId());
//    }


}