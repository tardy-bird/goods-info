package com.tardybird.goodsinfo.service;

import com.tardybird.goodsinfo.domain.Product;
import com.tardybird.goodsinfo.po.ProductPo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.Assert.*;
import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
class ProductServiceTest {

    @Autowired
    ProductService productService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getProductByGoodsId() {
        Integer goodsId = 100;
        List<ProductPo> productList = productService.getProductByGoodsId(goodsId);
        assertEquals(3,productList.size());
    }

    @Test
    void createProduct() {
        ProductPo product = new Product();
        product.setPicUrl("http://bing.com");
        product.setPrice(BigDecimal.TEN);
        product.setGoodsId(100);
        assertEquals(true,productService.createProduct(product));
    }

    @Test
    void updateProduct() {
        Product product = new Product();
//        product.setPicUrl("this is a new PICTURE");
//        product.setPrice(BigDecimal.valueOf(10.50));

        product.setId(3);

       assertEquals(true,productService.updateProduct(product));
    }

    @Test
    void getProductById() {
        Integer id = 1;
        Product product = productService.getProductById(id);
        assertEquals("http://yanxuan.nosdn.127.net/1f67b1970ee20fd572b7202da0ff705d.png",product.getPicUrl());
        assertEquals("[\"1.5m床垫*1+枕头*2\",\"浅杏粉\"]",product.getSpecifications());
    }
    @Test
    void deleteProduct() {
        Product product = new Product();
        product.setId(4);
        assertEquals(false,productService.deleteProduct(product.getId()));
    }
}