package com.tardybird.goodsinfo.service;

import com.tardybird.goodsinfo.domain.GoodsCategory;
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
    }

    @Test
    void getLevelOneCategories() {
    }

    @Test
    void getCategory() {
    }

    @Test
    void getLevelTwoByPid() {
    }

    @Test
    void createCategory() {
        GoodsCategory goodsCategory = new GoodsCategory();
        goodsCategory.setBeDeleted(false);
        goodsCategory.setName("this is a goods category(nick)");
        goodsCategoryService.createCategory(goodsCategory);
    }

    @Test
    void updateCategory() {
        GoodsCategory goodsCategory = new GoodsCategory();
        goodsCategory.setId(3);
        goodsCategory.setName("this is NEW category");

        goodsCategoryService.updateCategory(goodsCategory);
    }

    @Test
    void deleteCategory() {
        GoodsCategory goodsCategory = new GoodsCategory();
        goodsCategory.setId(3);

        goodsCategoryService.deleteCategory(goodsCategory.getId());
    }
}