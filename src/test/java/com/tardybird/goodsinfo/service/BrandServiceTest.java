package com.tardybird.goodsinfo.service;

import com.tardybird.goodsinfo.domain.Brand;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BrandServiceTest {

    @Autowired
    BrandService brandService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addBrand() {
        Brand brand = new Brand();
        brand.setName("brand");
        brand.setDescription("this is a brand(nick)");
        brand.setPicUrl("https://www.bing.com");
        brand.setBeDeleted(false);
        Boolean ok = brandService.addBrand(brand);
        Assertions.assertTrue(ok);
    }

    @Test
    void getAllBrands() {
        Integer page = 0;
        Integer limit = 10;
        String sort = "gmt_create";
        String order = "desc";
        Object brands = brandService.getAllBrands(page, limit, sort, order);
        System.out.println(brands);
    }

    @Test
    void getBrandsById() {
        Integer id = 1;
        Brand brand = brandService.getBrandById(id);
        System.out.println(brand);
    }

    @Test
    void getBrandsByCondition() {
        String id = "1";
        String sort = "gmt_create";
        String order = "desc";
        Object brands = brandService.getBrandsByCondition(id, "", 0, 10, sort, order);
        System.out.println(brands);
    }

    @Test
    void updateBrand() {
        Brand brand = new Brand();
        brand.setId(1);
        brand.setDescription("th is NEW brand, created by nick.");
//        Boolean ok = brandService.updateBrand(brand);
//        Assertions.assertTrue(ok);

        brandService.updateBrand(brand);
    }

    @Test
    void deleteBrand() {
        Boolean ok = brandService.deleteBrand(1);
        Assertions.assertTrue(ok);
    }

}