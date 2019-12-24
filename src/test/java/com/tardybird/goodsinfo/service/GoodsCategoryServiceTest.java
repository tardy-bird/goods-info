package com.tardybird.goodsinfo.service;

import static org.junit.Assert.*;
import com.tardybird.goodsinfo.domain.GoodsCategory;
import com.tardybird.goodsinfo.po.GoodsCategoryPo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GoodsCategoryServiceTest {

    @Autowired
    GoodsCategoryService goodsCategoryService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAllCategories() {
        Integer page=1;
        Integer limit=4;
        System.out.println(goodsCategoryService.getAllCategories(page,limit));
    }

    @Test
    void getCategory() {
        Integer id =122;
        GoodsCategoryPo categoryPo = goodsCategoryService.getCategory(id);
        assertEquals("艺术品",categoryPo.getName());
        assertEquals("0",categoryPo.getPid());
    }
    @Test
    void createCategory() {
        GoodsCategoryPo goodsCategory = new GoodsCategory();
        goodsCategory.setBeDeleted(false);
        goodsCategory.setName("this is a goods category(nick)");
        //test
        GoodsCategoryPo goodsCategoryPo =goodsCategoryService.createCategory(goodsCategory);
        assertEquals(false,goodsCategoryPo.getBeDeleted());
        assertEquals(goodsCategory.getName(),goodsCategoryPo.getName());
    }
    @Test
    void updateCategory() {
        GoodsCategory goodsCategory = new GoodsCategory();
        goodsCategory.setId(300);
        goodsCategory.setName("th is NEW category");
        assertEquals(true,goodsCategoryService.updateCategory(goodsCategory));
    }
    @Test
    void deleteCategory() {
        GoodsCategory goodsCategory = new GoodsCategory();
        goodsCategory.setId(3);
        assertEquals(true,goodsCategoryService.deleteCategory(goodsCategory.getId()));
    }
//    @Test
//    void getLevelTwoByPid(){
//        GoodsCategory goodsCategory = new GoodsCategory();
//        goodsCategory.setPid(127);
//       System.out.println( goodsCategoryService.getLevelTwoByPid(goodsCategory.getPid()));
//    }
}