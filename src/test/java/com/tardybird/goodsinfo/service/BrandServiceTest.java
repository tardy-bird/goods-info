package com.tardybird.goodsinfo.service;

import com.github.pagehelper.PageInfo;
import com.tardybird.goodsinfo.domain.Brand;
import com.tardybird.goodsinfo.po.BrandPo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;oomall_coupon
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

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
    //返回Pageinfo对象
    @Test
    void getAllBrands() {
        Integer page = 0;
        Integer limit = 10;
        String sort = "gmt_create";
        String order = "desc";
        Object brands = brandService.getAllBrands(page, limit, sort, order);
        System.out.println(brands);
        //assertEquals();
    }

    @Test
    void getBrandsById() {
        Integer id = 71;
        BrandPo brandPo = brandService.getBrandById(id);
        assertEquals("戴荣华",brandPo.getName());

    }
    //返回"Pageinfo"对象
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
        assertEquals(true,brandService.updateBrand(brand));
    }

    @Test
    void deleteBrand() {
        Brand brand = new Brand();
        brand.setId(73);
        assertEquals(true,brandService.deleteBrand(brand.getId()));
    }

}