package com.tardybird.goodsinfo.service;

import com.tardybird.goodsinfo.dao.GoodsDao;
import com.tardybird.goodsinfo.domain.Goods;
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

    @Test
    void getAllGoodsByConditions() {
    }

    @Test
    void getHotGoods() {
    }

    @Test
    void createGoods() {

        Goods goods = new Goods();
        goods.setName("new goods");
        goods.setBeDeleted(false);

        goodsService.createGoods(goods);
    }

    @Test
    void getGoodsById() {
    }

    @Test
    void getGoodsCount() {
    }

    @Test
    void getGoodsCategory() {
    }

    @Test
    void updateGoods() {
        Goods goods = new Goods();
        goods.setId(1);

        goods.setName("this is a NEW name");
        goodsService.updateGoods(goods);
    }

    @Test
    void deleteGood() {
        Goods goods = new Goods();
        goods.setId(1);

        goodsService.deleteGood(goods.getId());
    }


}